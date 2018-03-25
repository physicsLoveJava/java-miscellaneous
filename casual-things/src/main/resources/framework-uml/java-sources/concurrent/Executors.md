
## Executor 提供执行任务接口
## ExecutorService 提供执行任务的生命周期管理

## DelegatedExecutorService
包装类，提供对于executorService的实现

## FinalizableDelegatedExecutorService
在finalize方法中，将线程关闭

### newFixedThreadPool() --> LinkedBlockingQueue
保持执行任务的线程数量固定，适合保持资源的占用

### newCachedThreadPool() --> SynchronousQueue
执行短期异步任务的程序而言，这样可以提交效率，线程空闲时间默认一分钟

### newSingleThreadPool() --> LinkedBlockingQueue
保持提交的任务按照队列的顺序执行（FIFO, LIFO, 优先级）

使用线程池的好处：
1. 降低资源消耗
2. 提高响应速度
3. 提高线程的可管理性
4. 防止服务器过载


