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
#### fair
总结来说，reentrantlock公平模式，
获取锁：
一个线程尝试获取锁，如果当前同步状态为0（初始状态，也是无线程持有锁的时候），同时该线程
是等待队列中第一个，则获得锁；如果该同步状态不为0，则该线程必须是持有锁的线程，这样可以以
可重入的身份再次进入。
而如果该线程尝试获取锁的过程失败，则会将该线程添加至等待队列中：
1.如果该节点是头结点的下一个节点，那么他可以进行获取锁的尝试
2.如果该节点是普通的节点，那么它只能根据他前一个节点的状态进行后续操作（清除取消锁的节点，或者准备被阻塞，由当前的锁释放后，进行后续的唤醒）

释放锁：
当前线程是否是互斥状态持有的线程
当前的同步状态是否为可以取消锁的状态（递减状态值HoldCount，与加锁相反）
以上条件都满足，进行唤醒后续节点的操作

#### non-fair #unlock
fair unlock

#### non-fair #tryRelease
fair tryRelease

#### non-fair
总结来说，reentrantlock非公平模式
获取锁：
一个线程尝试获取锁，会首先进行闯入模式，也就是直接尝试将同步状态设置为1
需要注意的是，因为是CAS，Expect值为0，也就是当前的同步状态为0，表示当前没有线程持有该锁，
则闯入线程会直接设置同步状态，同时将该设置为互斥持有线程。
如果闯入模式失败，该线程会按照公平的模式进行，就是进入等待队列等待后续前节点的“通知”

释放锁：
和公平模式一样，因为同步状态是在队列同步器中，所以某个线程获得该同步的锁，并不会影响其他（例如同步队列，顶多是唤醒一下）

### ReentrantReadWriteLock
读读共享，读写互斥，写写互斥

针对某些读场景远远大于写场景的应用，这样可以提升并发量

内部实现使用ReadLock与WriteLock

#### readLock #acquireShared
```c 
protected final int tryAcquireShared(int unused) {
    /*
     * Walkthrough:
     * 1. If write lock held by another thread, fail.
     * 2. Otherwise, this thread is eligible for
     *    lock wrt state, so ask if it should block
     *    because of queue policy. If not, try
     *    to grant by CASing state and updating count.
     *    Note that step does not check for reentrant
     *    acquires, which is postponed to full version
     *    to avoid having to check hold count in
     *    the more typical non-reentrant case.
     * 3. If step 2 fails either because thread
     *    apparently not eligible or CAS fails or count
     *    saturated, chain to version with full retry loop.
     */
    Thread current = Thread.currentThread();
    int c = getState();
    //是否被写锁持有
    if (exclusiveCount(c) != 0 &&
        getExclusiveOwnerThread() != current)
        return -1;
    int r = sharedCount(c);
    //readerShouldBlock --> reader fair or reader non-fair
    //fair --> 是否有前任节点？
    //non-fair --> 是否是头节点，是否是等待的写节点（防止写线程饥饿问题）
    if (!readerShouldBlock() &&
        r < MAX_COUNT &&
        compareAndSetState(c, c + SHARED_UNIT)) {
        if (r == 0) {
            firstReader = current;
            firstReaderHoldCount = 1;
        } else if (firstReader == current) {
            firstReaderHoldCount++;
        } else {
            //记录下各个线程读持有数（可重入的特性）
            HoldCounter rh = cachedHoldCounter;
            if (rh == null || rh.tid != getThreadId(current))
                cachedHoldCounter = rh = readHolds.get();
            else if (rh.count == 0)
                readHolds.set(rh);
            rh.count++;
        }
        return 1;
    }
    return fullTryAcquireShared(current);
}
```
 
 #### readLock #doAcquireShared
与fair模式的ReentrantLock类似，判断当前节点的前节点是否是头节点，
然后尝试获取共享锁，如果可以获取到同步状态（大于等于0）,同时将头结点设置为传播状态


#### 响应中断的获取锁过程
```c 
public final boolean tryAcquireNanos(int arg, long nanosTimeout)
        throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    return tryAcquire(arg) ||
        doAcquireNanos(arg, nanosTimeout);
}
```

```c 
private boolean doAcquireNanos(int arg, long nanosTimeout)
        throws InterruptedException {
    if (nanosTimeout <= 0L)
        return false;
    final long deadline = System.nanoTime() + nanosTimeout;
    final Node node = addWaiter(Node.EXCLUSIVE);
    boolean failed = true;
    try {
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return true;
            }
            nanosTimeout = deadline - System.nanoTime();
            if (nanosTimeout <= 0L)
                return false;
            if (shouldParkAfterFailedAcquire(p, node) &&
                nanosTimeout > spinForTimeoutThreshold)
                LockSupport.parkNanos(this, nanosTimeout);
            if (Thread.interrupted())
                throw new InterruptedException();
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```
LockSupport.parkNanos()支持对于特定时间短的阻塞
spinForTimeoutThreshold，当等待时间小于1s时，进行for循环等待，而不是使用
LockSupport.parkNanos()

```c 
private void doAcquireInterruptibly(int arg)
    throws InterruptedException {
    final Node node = addWaiter(Node.EXCLUSIVE);
    boolean failed = true;
    try {
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                throw new InterruptedException();
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

LockSupport.park
阻塞返回时的情况：
1. 其他线程调用unpark参数相同
2. 其他线程中断阻塞线程
3. 幽灵唤醒



