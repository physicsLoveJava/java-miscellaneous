## pool2 design

---
Object factory

---

### PooledObjectFactory
1. makeObject 创建
2. destroyObject 销毁
3. validateObject 校验对象
4. activateObject 初始化对象
5. passivateObject 卸载对象

### BasePooledObjectFactory
提供基本的ObjectFactory,提供No-op方法，需要子类重写

---
PooledObject related

---

### PooledObject
提供池中对象一个包裹层，提供池中信息的

### PooledObjectState
池中对象的状态枚举
1. IDLE, 池中未使用对象
2. ALLOCATED, 池中使用的对象
3. EVICTION, 池中对象正在测试可能的销毁
4. EVICTION_RETURN_TO_HEAD，测试完成返回队列头部
5. VALIDATION， 校验过
6. VALIDATION_PREALLOCATED， 验证testBorrow
7. VALIDATION_RETURN_TO_HEAD，验证完成后返回队列头部
8. INVALID， 验证失败，准备销毁
9. ABANDONED， 被抛弃
10. RETURNING，正在返回池中

### DefaultPooledObject
提供基本的池对象

### PooledSoftReference
提供基于软引用的池对象

---
BaseObject

---

### BaseObject
提供默认的toString方法和字段toString

---
Object Pool

---

### ObjectPool
1. borrowObject
2. returnObject
3. invalidateObject
4. addObject
5. getNumIdle
6. getNumActive
7. clear
8. close

基本的池中对象操作，
* 取出对象
* 放回对象
* 预加载对象
* 销毁对象
* 获取空闲对象数
* 获取活跃对象数
* 清除池中所有对象
* 关闭对象池

### BaseObjectPool
基本的实现

### ProxiedObjectPool
提供基于代理类的对象池，可以进行池使用的监控

### SoftReferenceObjectPool
提供基于软引用的对象池

### BaseGenericObjectPool
基本通用的对象池

### GenericObjectPool
基本的通用对象池，默认实现

### UserTracking
用户行为追踪

### GenericObjectPoolMXBean
JMX beanInfo

### DefaultPooledObjectInfoMBean
JMX beanInfo

### DefaultPooledObjectInfo
default info


---
Proxy Handler

---

### ProxySource
创建代理对象，解析代理对象

### JDKProxySource
### CglibProxySource

### BaseProxyHandler
基本的代理方法处理

### InvocationHandler
### methodInterceptor

---
config

---
### BaseObjectPoolConfig
### GenericObjectPoolConfig


