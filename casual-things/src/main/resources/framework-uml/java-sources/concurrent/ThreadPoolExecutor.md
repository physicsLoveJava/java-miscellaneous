
runState: 
Running: 接受新的任务和处理队列中的任务
Shutdown: 不接受新的任务，但是处理队列中的任务
Stop: 不接受新任务，不处理队列中的任务，同时中断处理中的任务
Tidying: 所有的任务都结束了，将会执行terminated方法
Terminated: terminated方法执行完毕

任务队列
BlockingQueue<Runnable> workQueue:

工作线程
HashSet<Worker> workers;

工作线程锁
ReentrantLock mainLock;

awaitTermination condition
Condition termination = mainLock.newCondition();

线程创建工厂
ThreadFactory threadFactory

拒绝执行处理器
RejectedExecutionHandler handler;

空闲线程等待时间
long keepAliveTime;

是否允许核心线程保持主动状态，即使当他空闲时
boolean allowCoreThreadTimeOut;

保持活跃的最少工作线程数
int corePoolSize;

池最大值
int maximumPoolSize;

## execute
执行给定任务在未来的某个时刻，
如果当前线程池被关闭，或者容量到达顶峰，则该任务会被拒绝处理器执行

执行过程：
1. 如果少于核心线程数的线程，则新建一个线程，并将当前任务给它
2. 如果一个任务可以成功的入队，需要对该线程进行double-check, 防止线程池被关闭，或者线程已经死亡
3. 如果任务无法入队，则尝试新增线程，如果失败，则拒绝任务

## purge
清除队列中的取消的任务

## reject
拒绝任务

## remove
清除任务

### 拒绝策略
1. AbortPolicy
    just throw exception
2. DiscardPolicy
    just throw task(ignore it)
3. DiscardOldestPolicy
    抛弃队列中最老的任务，添加新的任务
4. CallersRunPolicy
    在线程池没有关闭前，执行任务

### Worker对象
Thread 执行的线程
Runnable 执行的任务

执行任务的逻辑；
1. 循环去获取任务
2. beforeExcute
3. task run
4. afterExcute 会将task执行过程中的异常，传递给afterExecute
5. before, after方法执行失败，会将该线程销毁



