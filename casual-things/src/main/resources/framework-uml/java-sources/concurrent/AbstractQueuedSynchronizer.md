
AQS的主要作用，原子性地管理同步状态，阻塞和非阻塞线程以及等待队列。
AQS的应用：各种形式的互斥锁，读写锁，信号量，栅栏，延迟计算，事件通知和交付队列。

AQS的功能：
1. acquire, 在同步状态允许线程执行前阻塞调用线程
2. release, 改变同步状态是的一个或者多个线程阻塞状态解开
3. 非阻塞的同步尝试tryLock
4. 可选的超时
5. 基于中断的取消
6. 基于排他状态与共享状态的锁实现

性能要求
1. 对于吞吐量高的系统（意味着保证不发生饥饿）
2. 对于资源控制，公平更加重要（允许较差的吞吐量）

AQS设计与实现
1. acquire

```c
while (synchronization state does not allow acquire) {
    enqueue current thread if not already queued;
    possibly block current thread;
}
dequeue current thread if it was queued;
```

2. release

```c
update synchronization state;
if (state may permit a blocked thread to acquire)
unblock one or more queued threads;
```

阻塞：
LockSupport对于线程阻塞的支持：
1. pack
2. unpack

等待队列：
MCS locks and CLH locks
CLH 被选择，更容易处理取消和超时

Node 
type: 
1. 共享模式
2. 互斥模式

waitStatus:
state:
1. cancelled: 被取消
2. signal: 继承节点需要unparking
3. condition: 线程在等待条件变量
4. propagate: 下一个共享获取的节点需要无条件的传播下去

detailed:
1. signal(-1)
当前节点的继任节点通过park被阻塞,所以当前节点需要在它释放锁或者取消锁的时候，
将它的继任节点unpark. 为了避免竞争，获取锁方法必须首先表示他需要一个唤醒（signal），
然后尝试原子性的获取锁，然后失败阻塞。
2. cancelled(1)
当前节点因为超时或者中断被取消，节点都不会保持这个状态，线程拥有取消状态从来不会再次阻塞
3. condition(-2)
当前节点正在条件变量队列上
It will not be used as a sync queue node
until transferred, at which time the status
will be set to 0. (Use of this value here has
nothing to do with the other uses of the
field, but simplifies mechanics.)
4. propagate(-3)
一个共享性的释放应该传播到其他节点,默认是0
非负数的值表示节点不需要唤醒（signal）


