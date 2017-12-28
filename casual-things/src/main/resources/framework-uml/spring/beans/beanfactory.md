
## BeanFactory

interface BeanFactory
1. 提供Bean的获取
2. 检查是否存在
3. 对于容器中bean类型的判断
4. 获取容器中bean的别名

interface HierarchicalBeanFactory
1. 获取祖先容器
2. 检查当前容器是否存在

interface ListableBeanFactory
1. 是否存在bean，bean的统计信息
2. 获取指定类型的bean
3. 获取指定bean的注解

interface AutowireCapableBeanFactory
1. 创建bean实例
2. 转配bean进容器
3. 计算bean依赖
4. 初始化bean
5. bean的后置处理器
6. 销毁bean

interface ConfigurableBeanFactory
1. 转换服务，处理器，属性设置器（util）
2. 根据scope进行注册bean
3. 注册别名
4. 对bean的判断，是否factoryBean
5. 获取bean的依赖
6. 销毁bean

interface ConfigurableListableBeanFactory
1. 忽略类型
2. 获取bean定义
3. 对容器进行冻结

interface SingletonBeanRegistry
1. 注册单例
2. 获取单例
3. 获取单例互斥变量

interface AliasRegistry
1. 注册别名
2. 删除别名
3. 查询别名
4. 判断是否是别名

interface BeanDefinitionRegistry
1. 针对bean进行接口的再定义


