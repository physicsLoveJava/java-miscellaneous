
## 1. 并发总能改进性能？
阿玛达定律，并发程序的性能提升受可并行化部分的影响，同时在线程数量较多的情况下，线程间的
频繁调度切换，反正增加了系统的开销

r = 1 / (1 - p + p/q) p 串行执行部分，q是线程数量

## 2. 编写并发程序不需要考虑系统原有的设计？
并发程序的目的和时机对系统的结构产生巨大影响，可能一个很小的需求，就可能对某一个局部结构产生很大的影响
//TODO 并发模式

## 3. 在使用Web容器时，不需要了解并发问题？
只要容器在干什么，才能更好地使用容器
//TODO 了解并发相关的工具与容器的工作原理

## 4. Thread如何中止运行？interrupt的机制是如何运作的？
Thread在运行结束自己的任务后，会自动终止，而如果程序本身一直在运行中，则无法进行中止，
（java 设计者认为 直接中止任何一个线程都是不合理的，java 提供一种协作机制 interrupt），interrupt可以为线程提供通知消息，
以表明其他线程或者自己想进行一次中断。
Thread Interrupt:
1. 当前class的 wait, join, sleep, interrupt方法会清楚中断状态，然后接受InterruptedException
2. blocked in i/o , interrupt方法会设置中断状态, ClosedByInterruptException
3. blocked in selector, interrupt方法会导致selector立即返回，同时设置中断状态
4. 其他情况，中断状态会被设置

thread#isInterrupted, 只会检查线程的中断状态，不会改变
Thread#interrupted，会检查线程的中断状态，同时清除线程的中断状态

### open-jdk
volatile jint _interrupted; interrupt state
同时 interrupt 方法设置状态，然后发布中断事件
is_interrupted(thread, isClear) 检查中断状态，是否清除状态 

## 5. Thread的stop，resume, suspend方法为什么不可用，怎么解决？
thread#stop 停止一个线程会导致它解锁它拥有的锁，导致监视器处于不一致的状态，应该使用
volatile变量去控制线程的终止，或者使用interrupt协作（主要用于较长的周期，或者等待某些特定的事件）

thread#suspend 由于该方法可能造成潜在的死锁问题，例如某个线程持有锁，当该线程进行挂起时，其他线程都无法访问，
而当该线程进行时，它需要获得资源的锁，这个时候就发生死锁。
thread#resume 该方法配合suspend使用，这就是意味着该方法被废除

## 6.什么是线程池（thread pool）？线程池的原理是什么？
线程池主要提供一种将任务执行与具体的线程使用以及调度解耦的方式，让你不需要显式地创建、调度线程，
只需要关注任务的执行，以及任务执行的方式。同时，由于创建和销毁线程都是比较耗费时间的操作，线程池通过
复用线程来达到提高服务程序效率的目的。

1. 无界线程池，newCachedThreadPool
2. 有界线程池，newFixedSizeThreadPool
3. 单线程，newSingleThreadExecutor
4. 定时线程池，newScheduledThreadPool

## 7.线程池中的任务彼此间有关联吗？
线程池中的任务遵循内存一致性的原则（happens-before）,发生在之前的任务，对后面的任务来说是可见的。

## 8.线程池中的shutdown, shutdownNow, awaitTermination有什么区别？
shutdown方法执行后，新的任务不会被接受进行执行，同时线程池会在之前的提交的任务执行完后关闭。
shutdownNow方法执行后，会尝试去关闭所有进行中的任务，取消所有等待中的任务，然后将这些等待的任务返回。
同时，由于没有办法很好的进行任务的关闭，通常是通过中断，对执行的任务济宁取消，如果任务本身无法对中断进行响应，
则该任务无法停止。
awaitTermination方法执行后会进行阻塞，直到满足下面的一个条件：
1. shutdown后，所有的任务都执行完毕 --> 返回true
2. timeout --> 返回false
3. 当前线程中断 --> throw InterruptedException

## 9.自定义线程池有哪些注意点？
1. coreSize, maximumPoolSize
2. ThreadFactory, 可以根据自定义条件创建线程
3. keep-alive times
4. Queuing, 在超过当前允许的线程数时，可以将任务进行排队等待操作
    排队的方式：
    1. 直接交付，hands-off, SynchronousQueue(增长速度不能超过处理速度，多出的任务会被拒绝)
    2. 无界队列，LinkedBlockingQueue(适合迅速增长的情况)
    3. 有限队列，ArrayBlockingQueue(适合控制资源使用的场景，但是很难预测其大小)
    （1. 长队列+小型池 低吞吐量）（2. 短队列+大型池 大量的线程调度，降低性能）
5. 拒绝任务策略，（执行shutdown, 或者有限容量的队列）
    1. 中止策略，直接抛出RejectedExecutionException
    2. 调用者执行策略，执行线程执行任务
    3. 丢弃策略， 直接丢弃
    4. 丢弃最旧的
6. 钩子方法
   1. beforeExecute
   2. afterExecute
   
## 10. 线程池中的如何做审计信息？


## 11. 线程池多数都会在线程抛出异常后，重新创建一个线程，它具体是如何做的？
在每次添加任务时，都会对当前线程池中的核心数进行判断，如果少于指定的核心数，都会添加新的工作线程，并把任务分配下去。









