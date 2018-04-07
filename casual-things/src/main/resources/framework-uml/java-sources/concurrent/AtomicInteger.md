
## 原子变量

将变量的操作以原子的方式进行。为什么原子的方式可以保证线程安全？--->竞争条件

### compareAndSet(expect, update)
如果当前值==预期值，则以原子方式更新为给定的值，成功返回true, 否则返回false（并不会修改原来的值）

compareAndSet的源码：
```c++
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
  UnsafeWrapper("Unsafe_CompareAndSwapInt");
  oop p = JNIHandles::resolve(obj);
  jint* addr = (jint *) index_oop_from_field_offset_long(p, offset);
  return (jint)(Atomic::cmpxchg(x, addr, e)) == e;
UNSAFE_END
```

## AtomicIntegerFieldUpdater
需要保证类的串行化需求时

1. 字段必须是volatile
2. 对于父类字段，子类不能直接操作
3. 只能是实例变量
4. 只能是可修改变量，而不是final
5. AtomicIntegerFieldUpdater,只能修改int类型，不能修改包装类型（compareAndSwapInt），修改包装类型需要使用AtomicReferenceFieldUpdater

## CAS
乐观锁的机制

ABA问题，仅仅通过比较状态的方法并不能保证节点是不发生变化的---> AtomicStampedReference

## 非阻塞算法
在并发上下文中，非阻塞算法是一种允许线程在阻塞其他线程的情况下访问共享状态的算法啊，在算法中如果一个线程的挂起没有导致其他的线程挂起，就是非阻塞算法。

