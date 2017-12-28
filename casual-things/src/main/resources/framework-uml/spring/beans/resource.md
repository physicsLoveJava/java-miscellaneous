

interface InputStreamSource
1. 字节流

interface Resource extends InputStreamSource
1. 是否存在
2. 是否可读
3. 是否打开
4. 获取地址信息
5. 获取文件源
5. 资源大小
6. 文件名称和描述

abstract class AbstractResource implements Resource
抽象类实现

class InputStreamResource extends AbstractResource
1. 字节流源
2. 内部使用字节流

abstract class AbstractFileResolvingResource extends AbstractResource

class BeanDefinitionResource extends AbstractResource
1. bean定义源

class DescriptiveResource extends AbstractResource

class FileSystemResource extends AbstractResource implements WritableResource
1. 文件系统源
2. 内部使用文件

interface WritableResource extends Resource
1. 源是否可写

class PathResource extends AbstractResource implements WritableResource
1. 使用jdk1.7的path做源

interface ContextResource extends Resource
上下文源

class ServletContextResource extends AbstractFileResolvingResource implements ContextResource
servletContext做源

class VfsResource extends AbstractResource 
虚拟文件系统forJBoss
