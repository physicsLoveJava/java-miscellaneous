
## 1. 简要描述线程的各种状态，以及其转化过程

传统意义上的进程，主要有以下状态：
1. 新的，刚刚被创建
2. 运行，执行正在执行
3. 等待，进程等待某个事件的发生（I/O完成）
4. 就绪，进程等待分配处理器
5. 中止，进程完成执行

创建 -- 就绪 -- I/O等待，事件等待 -- 运行 -- 中止

在java中，线程除了以上的状态，还具备等待锁的部分

1. 创建 -- 就绪 -- I/O等待，事件等待 -- 运行 -- 中止
2. 创建 -- 就绪 -- 运行（持有锁）-- 运行（等待锁） -- 中止

## 2. Thread中线程隔离（Thread-local）是如何实现的？
通过Thread内部维护一个ThreadLocalMap维护ThreadLocal与其值的哈希表，将不同线程的下的变量副本，保存在每个线程的内部。
由于Map.Entry使用的WeakReference,同时这个WeakReference维护的引用是ThreadLocal，只有当前的ThreadLocal不会引用，或者Thread被销毁，
等到下次GC，相关的ThreadLocal副本都会被回收。
使用WeakReference而不是强引用的主要原因是，当一个类引用WeakReference时，可以进行ThreadLocal的复制和重新设置，而不用显式地从
ThreadLocalMap中删除，因为WeakReference在没有其他对象引用它的时候，它会自动的在gc时被回收。

## 3. Thread中异常处理的实现原理是什么？为什么这么做？
通过设置uncaughtExceptionHandler，对runnable抛出的uncheckedException进行处理，
JVM通过调用dispatchUncaughtException, 或者ThreadGroup.uncaughtException进行方法的回调
原则是线程异常应该由自己处理

## 4. java内置锁的实现原理是什么？
java synchronized关键字是通过两个指令（monitorenter, monitorexit)实现的，
Monitor(管程)保证同一个时刻，只有一个进程在管程内活动，即管程内定义的操作在同一时刻被一个进程调用，
同时为了能够保证进程以设计的顺序执行，还需要设置condition变量，让进入管程而无法继续执行的进程阻塞自己。

## 5. javaReentrantLock实现原理和采用的技术方式是什么？

## 6. java是否可以实现悲观锁和乐观锁（StampedLock）？

## 7. java原子变量实现的原理是什么？

## 8. javaThread调用栈的实现原理是什么？

## 9. WeakHashMap的应用场景有哪些？

## 10. guava cache的实现原理是什么？

## 11. java unsafe中getXXXVolatile是如何实现的？
