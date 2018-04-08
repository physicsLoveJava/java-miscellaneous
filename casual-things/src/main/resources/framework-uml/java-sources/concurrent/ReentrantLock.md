## ReentrantLock

特性:
1. 可重入
2. 公平与非公平
3. 互斥锁
4. 非阻塞获取锁，timed的获取锁

额外特性：
1. 是否由当前线程持有

### Lock
1. 当前线程持有，holdCount++
2. 其他线程持有，静止等待
3. 无线程持有，获取锁，holdCount=1

#### fair #acquire
1. 
```c
acquire(1);
```
2. 
```c
在互斥模式下获取锁，忽略中断
1. 至少一次tryAcquire
2. 否则线程入队，重复阻塞和非阻塞（tryAcquire）,直到成功
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

```c
互斥模式下，尝试获取锁
1. 该方法应该查询，对象的状态是否允许他在互斥模式获取锁，如果是，就获取锁, 该方法总是在线程进行获取锁操作时触发，
2. 如果方法失败，获取方法应该将线程入队，知道它被其他线程通过释放锁进行唤醒（release -> signal）
protect void tryAcquire(int state) {}
```

公平版本的tryAcquire
公平版本下，
1. 该线程为没有等待者或者是等待队列中第一个才获取锁。
2. 递归进行调用（可重入）
```c 
protected final boolean tryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        //队列中的第一个，设置获取状态，将互斥拥有者设置为当前线程
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {//可重入的实现
        int nextc = c + acquires;//holdCount++
        if (nextc < 0)//holdCount < Integer.MAX_VALUE
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

addWaiter添加等待线程（可以指定等待者的模式）

acquireQueued
对线程已经在队列中的，以互斥非中断的方式进行获取锁
```c 
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            //检查队列头部，进行获取锁操作
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

shouldParkAfterFailedAcquire
是否应该在获取锁失败后进行阻塞线程
1. 如果前节点已经标注唤醒继任节点的状态，则返回true(由release进行唤醒)
2. 如果前节点是取消锁状态，则清除后续的取消锁节点，然后返回false
3. 否则，设置前节点为唤醒状态（唤醒继任节点）

#### fair #release
1. 
```c
1. 如果当前线程持有锁，则holdCount--
2. 如果holdCount为0，则释放锁
3. 如果当前线程不是持有者，则IllgealMonitorStateException

sync.release(1);
```
2. 
```c 
如果释放成功，则唤醒一个或多个线程
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

3.
```c 
protected final boolean tryRelease(int releases) {
    int c = getState() - releases;
    //线程持有者和线程不匹配
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    //holdCount == 0
    if (c == 0) {
        free = true;
        setExclusiveOwnerThread(null);
    }
    //holdCount减少
    setState(c);
    return free;
}
```

4. unparkSuccessor
唤醒节点的继任者
```c 
private void unparkSuccessor(Node node) {
    /*
     * If status is negative (i.e., possibly needing signal) try
     * to clear in anticipation of signalling.  It is OK if this
     * fails or if status is changed by waiting thread.
     */
    int ws = node.waitStatus;
    if (ws < 0)
        compareAndSetWaitStatus(node, ws, 0);

    /*
     * Thread to unpark is held in successor, which is normally
     * just the next node.  But if cancelled or apparently null,
     * traverse backwards from tail to find the actual
     * non-cancelled successor.
     */
    Node s = node.next;
    if (s == null || s.waitStatus > 0) {
        s = null;
        for (Node t = tail; t != null && t != node; t = t.prev)
            if (t.waitStatus <= 0)
                s = t;
    }
    if (s != null)
        LockSupport.unpark(s.thread);
}
```

#### fair

#### non-fair

#### non-fair #acquire
1. 
```c 
执行锁，尝试立即的闯入，候选进行正常的获取锁（闯入不成功的话）
final void lock() {
    //闯入（并不会进入队列）
    if (compareAndSetState(0, 1))
        setExclusiveOwnerThread(Thread.currentThread());
    else
        acquire(1);
}
```

#### non-fair #tryAcquire
1. 
```c 
protected final boolean tryAcquire(int acquires) {
    return nonfairTryAcquire(acquires);
}
```

2. 
```c 
tryLock需要非公平性的
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        //闯入
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

#### non-fair #unlock
fair unlock

#### non-fair #tryRelease
fair tryRelease




