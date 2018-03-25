
## Executor 提供执行任务接口
## ExecutorService 提供执行任务的生命周期管理

## DelegatedExecutorService
包装类，提供对于executorService的实现

## FinalizableDelegatedExecutorService
在finalize方法中，将线程关闭

### newFixedThreadPool() --> LinkedBlockingQueue
### newCachedThreadPool() --> SynchronousQueue
### newSingleThreadPool() --> LinkedBlockingQueue
