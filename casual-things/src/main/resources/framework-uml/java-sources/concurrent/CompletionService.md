
## CompletionService
将异步任务和已经完成的任务耦合解开，使得程序可以同时使用Executor和BlockingQueue的能力，
1. 提交任务
2. 获取已经完成的结果

### take 获取已经完成的任务，如果没有则等待
### poll 获取已经完成的任务，如果没有则返回null

## ExecutorCompletionService
接口的具体实现类

通过QueuingFuture覆写done方法，将完成的任务添加到完成的队列中，然后用户可以透明地从中去结果

而FutureTask的特点是，RunnableFuture的实现，也就是它可以和Executor兼容，当任务被Executor处理，意味着其run方法被调用
这样FutureTask可以通过get方法获取其任务执行的结果。

