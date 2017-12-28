
interface BeanDefinitionReader
1. 获取BeanDefinition注册信息
2. 获取资源加载器
3. 获取bean名称生成
4. 加载bean定义

interface EnvironmentCapable
1. 获取环境信息

interface ResourceLoader
1. 获取指定的资源
2. 获取类加载器

interface PropertyResolver
1. 是否包含属性
2. 获取属性的值
3. 解析占位符内容

interface Environment 
1. 获取激活的配置信息
2. 获取默认的配置信息

interface BeanNameGenerator
1. 根据bean定义生成bean名称

interface BeanDefinitionDocumentReader
1. 加载解析成文档信息的bean定义

class BeanDefinitionParserDelegate
1. 具体实行解析的对象

