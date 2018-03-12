
## java collections

### comparator
与相等保持一致性（c.compare(e1, e2) == 0 EQ e1.equals(e2)）
general contract for set （总体契约对于集合来说）
如果某个比较器不满足与相等保持一致性，则应该给出注释，说明此比较器不满足相等保持的一致性

如果针对于可以序列化的集合来说，Comparator也应该实现序列化的接口
比较器的compare方法应该保持等价关系，否则可能排序报错（jdk1.7与jdk1.6的兼容性问题）
http://www.oracle.com/technetwork/java/javase/compatibility-417013.html#incompatibilities

1. 自反性, sign(com(x, y)) == -sign(com(y, x))
2. 传递性, sign(com(x, y)) >= 0 && sign(com(y, z)) >= 0 ==> sign(com(x, z)) >=0
3. 对称性, sign(com(x, y)) == 0 => sign(com(x, z)) == sign(com(y, z))

### Comparable
comparable是实现该接口类的自然方法，如果和null进行比较，应该抛出NullPointerException，即使存在相等关系

相等关系和等价关系应该保持一致，否则对于SortSet与SortMap来说，可能会出现行为的怪异
如果存在某两个元素!e1.equals(e2) && c.compare(e1, e2) == 0, 则对于未指定Comparator的SortSet来说，
同样的添加两个元素，e1, e2, 第二个元素并不会被添加，因为对于SortSet而言，相等才是添加的标准，而等价并不是

### Iterable
能够让对象使用for-loop语句

### Collection
集合容器
在java的实现中，集合的子类都需要对外提供两种类型的构造器，一种是无参数构造器，另一种是提供单个Collection为参数的构造器，
提供Collection构造器主要是方便用于生成不同视图的集合。

### List
有序集合，使得可以按照索引的顺序进行元素的访问，以及对列表中的元素进行检索。
相对于集合接口，额外的条例有：
* <tt>iterator</tt>, <tt>add</tt>, <tt>remove</tt>, <tt>equals</tt>, <tt>hashCode</tt> methods
新增的方法：(契约接口)
positional access
1. E get(index);
2. set(index, e);
3. add(index, e);
4. E remove(index);

search operations
1. int indexOf(Object);
2. int lastIndexOf(Object);

list iterator (support add and remove operations)
1. ListIterator listIterator();
2. ListIterator listIterator(index);

view
1. List<E> subList(fromIndex, toIndex)

equals && hashCode
equals方法要求，两个list拥有相同顺序的元素
hashCode则保证两个list相等，hashCode的前提下给出计算方法
```code
int hashCode = 1;
for (E e : list)
    hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
```

### Set
Set接口主要是抽象数学中的集合，即不存在两个相等价的元素，在java的实现中，顶多允许一个空值存在。
hashCode是将所有元素是hashCode相加

### Queue
Queue是用于在处理之前保存元素的集合，同时保证某种顺序性，
以外，Queue提供两种类别的操作，
1. 当操作不满足条件是直接抛出异常
2. 当操作不满足条件返回特定的值

Queue相对于Collection定义的接口方法的不同
<table BORDER CELLPADDING=3 CELLSPACING=1>
 <caption>Summary of Queue methods</caption>
  <tr>
    <td></td>
    <td ALIGN=CENTER><em>Throws exception</em></td>
    <td ALIGN=CENTER><em>Returns special value</em></td>
  </tr>
  <tr>
    <td><b>Insert</b></td>
    <td>{@link Queue#add add(e)}</td>
    <td>{@link Queue#offer offer(e)}</td>
  </tr>
  <tr>
    <td><b>Remove</b></td>
    <td>{@link Queue#remove remove()}</td>
    <td>{@link Queue#poll poll()}</td>
  </tr>
  <tr>
    <td><b>Examine</b></td>
    <td>{@link Queue#element element()}</td>
    <td>{@link Queue#peek peek()}</td>
  </tr>
 </table>
 
 Queue分为FIFO和LIFO两种
 Head指针负责删除元素，Tail指针负责新增元素
 
 关于Offer方法，由于在有限容量的场景下，add方法不满足的情况可能是常态，所以offer方法提供了返回特定值的情况，而不是抛出异常
 poll方法类似，相对于element方法，提供了在集合为空是返回null
 
 queue不提供equals和hashCode，因为集合元素的顺序性。
 
 ### Deque
 双端队列，提供在队列开始与结束部分的添加和删除操作，提供有容量与无容量限制的功能。
 提供与Queue一直的接口，只不过将操作进行了队列开始与队列结束的区分。
 特殊方法：
 boolean removeFirstOccurrence(Object o);
 boolean removeLastOccurrence(Object o);
 
 ### SortedSet
 提供全序排列，同时满足顺序与等价一致性
 
 ### NavigableSet
 提供更加全面的值比较（<, <=, >, >=）
 同时对于Sorted提供的subSet, headSet, tailSet进行了边界是否包含的补充方法
 
 ### RandomAccess
 标记接口，判断list是否可以随机访问，用以区分线性表，提供泛化算法的计算性能
 
 ### Enumeration
 
 ### AbstractCollection
 ### AbstractList
 ### AbstractSet
 ### AbstractQueue
 ### AbstractSequentialList
 
 ### Vector
 growable array of objects (size is grow and shrink by the add and remove methods)
 vector的iterator采取fail-fast策略，也就是说只要是并发修改，会立刻报错，而Enumeration的实现并不会。
 这并不能保证程序上的问题，只是提供一种检测程序bug的方法。
 
 vector方法是采取同步的，而如果不需要线程安全的实现，可以使用ArrayList
 
 1. capacityIncrement,每次vector扩张的大小，如果capacityIncrement小于等于0，则每次长度增加一倍
 2. System.arraycopy(arr, index, arr, index + 1, len - index);
 
 ### ArrayList
 resizable array of objects
 相当于无锁版的Vector
 1. 没有capacityIncrement, 每次扩张增加双倍的空间
 
 ### Stack
 
 ### ArrayDeque
 
 ### PriorityQueue
 
 ### HashMap
 1. 遍历需要访问所有的桶
 
 ### LinkedHashMap
 1. 遍历只需要访问有数据的桶
 
 ### TreeMap
 ### EnumMap
 ### WeakHashMap
 
 
 
