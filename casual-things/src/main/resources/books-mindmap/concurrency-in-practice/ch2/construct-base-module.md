
### 同步容器

1. 客户端加锁
2. 并发方法控制（还记得ConcurrentModificationException?）

### 并发容器

* ConcurrentHashMap
* CopyOnWriteArrayList, CopyOnWriteSet
* BlockingQueue

#### ConcurrentHashMap

分段锁（Lock Striping) 

空与元素数量的动态性变得不重要了

#### CopyOnWriteArrayList

通过迭代期间，创建并重新发布一个新的容器副本，实现可变性

#### BlockingQueue和生产者-消费者模式

### 阻塞方法和中断方法

线程阻塞时，通常被挂起，并处于阻塞状态（Blocked, Waiting, Timed_waiting)

中断是一种协作机制，一个线程不能强制其他线程停止正在执行的操作而去执行其他的操作

#### 同步工具类

##### 闭锁（Latch)
1. 确保所有需要的资源在初始化之后才能继续
2. 确保某个服务的依赖服务都启动之后才能启动
3. 等待知道某个操作的所有参与者都就绪才执行

##### FutureTask
1. 等待运行，
2. 正在运行
3. 运行完成

##### 信号量（Semaphore)
表示某种资源的数量

##### 栅栏（Barrier)

阻塞一组线程直到某个事件发生

### 构建高效的缓存服务

