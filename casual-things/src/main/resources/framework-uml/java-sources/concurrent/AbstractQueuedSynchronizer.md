
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

