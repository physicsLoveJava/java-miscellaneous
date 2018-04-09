
## ConcurrentHashMap 
相比hashTable的全部操作线程安全，提升了更好的并发性能，所有的操作按照hashTable的标准来设计

get过程是不需要加锁的，同时这意味获取的值是最邻近的更新值，同时迭代器也是反映哈希表某个点的状态，
虽然迭代器不会抛出并发修改的异常，但是本身迭代器的设计是为单个线程使用的.

哈希表通过ConcurrencyLevel参数表示哈希表内部的分片大小，通过内部分片的方式，是的并发更新不需要
征用，由于rehash需要调整表的分片内全部的元素，所以尽可能地先指明哈希表的大小。

分段锁数组的长度必须是2的次方

```c
public ConcurrentHashMap(int initialCapacity,
                         float loadFactor, int concurrencyLevel) {
    if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
        throw new IllegalArgumentException();
    if (concurrencyLevel > MAX_SEGMENTS)
        concurrencyLevel = MAX_SEGMENTS;
    // Find power-of-two sizes best matching arguments
    int sshift = 0;
    int ssize = 1;
    //计算需要的segments大小
    while (ssize < concurrencyLevel) {
        ++sshift;
        ssize <<= 1;
    }
    //shift位数
    this.segmentShift = 32 - sshift;
    this.segmentMask = ssize - 1;
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    //计算segement table容量，也需要保证是2的次方
    int c = initialCapacity / ssize;
    if (c * ssize < initialCapacity)
        ++c;
    int cap = MIN_SEGMENT_TABLE_CAPACITY;
    while (cap < c)
        cap <<= 1;
    // create segments and segments[0]
    Segment<K,V> s0 =
        new Segment<K,V>(loadFactor, (int)(cap * loadFactor),
                         (HashEntry<K,V>[])new HashEntry[cap]);
    Segment<K,V>[] ss = (Segment<K,V>[])new Segment[ssize];
    UNSAFE.putOrderedObject(ss, SBASE, s0); // ordered write of segments[0]
    this.segments = ss;
}
```

### 定位segment
1. 再散列 （保证数据在各个segment中分布平均）
2. 根据移位和掩码 （高位hash，保证数据的分布）
```c 
private int hash(Object k) {
    int h = hashSeed;

    if ((0 != h) && (k instanceof String)) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCode();

    // Spread bits to regularize both segment and index locations,
    // using variant of single-word Wang/Jenkins hash.
    h += (h <<  15) ^ 0xffffcd7d;
    h ^= (h >>> 10);
    h += (h <<   3);
    h ^= (h >>>  6);
    h += (h <<   2) + (h << 14);
    return h ^ (h >>> 16);
}

private Segment<K,V> segmentForHash(int h) {
    long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
    return (Segment<K,V>) UNSAFE.getObjectVolatile(segments, u);
}
```

### 获取值
整个过程不需要锁，不过不能保证整个状态不变
不需要加锁的原因是HashEntry使用volatile

```c 
public V get(Object key) {
    Segment<K,V> s; // manually integrate access methods to reduce overhead
    HashEntry<K,V>[] tab;
    //hash key
    int h = hash(key);
    long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
    if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
        (tab = s.table) != null) {
        //找到hash bucket 后遍历
        for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                 (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
             e != null; e = e.next) {
            K k;
            if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                return e.value;
        }
    }
    return null;
}

//保证扩容的语义
static final class Segment<K,V> extends ReentrantLock implements Serializable {
    transient volatile HashEntry<K,V>[] table;
}

//保证更新的语义
static final class HashEntry<K,V> {
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;
}
```

### 插入值
插入需要考虑，单个segment的多线程共享性，需要加锁，同时判断segment里的hashTableEntry是否需要扩容

需要注意的是ConcurrentHashMap中segments是final数组，虽然初始化各个线程都可以看见它的地址
但是如果segments如果某个索引出添加元素，依然需要做可见性保证，剩下的操作就是哈希表的操作

```c 
public V put(K key, V value) {
    Segment<K,V> s;
    if (value == null)
        throw new NullPointerException();
    int hash = hash(key);
    int j = (hash >>> segmentShift) & segmentMask;
    if ((s = (Segment<K,V>)UNSAFE.getObject          // nonvolatile; recheck
         (segments, (j << SSHIFT) + SBASE)) == null) //  in ensureSegment
        s = ensureSegment(j);
    return s.put(key, hash, value, false);
}
```

### size操作
对哈希表进行大小的测量，首先通过两次不加锁统计，如果数目相等，直接返回；
如果不相等，在进行segments数组加锁，进行统计，然后解锁。




