## pool2 design

### PooledObjectFactory
1. makeObject 创建
2. destroyObject 销毁
3. validateObject 校验对象
4. activateObject 初始化对象
5. passivateObject 卸载对象

### BasePooledObjectFactory
提供基本的ObjectFactory,提供No-op方法，需要子类重写

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



