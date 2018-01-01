
## classloader

defineClass：用于将byte数组解析成JVM能够识别的Class对象，有了这个方法意味着我们不仅仅可以通过class文件实例化对象，还可以通过别的方式。

findClass: 用于实现类的加载规则

resolveClass: 用于链接class文件

java中的对象数组并不是由classloader进行加载，而是运行时确定的。

## 委托加载机制

Bootstrap : 加载JVM自身工作需要的类，由JVM自己控制。

ExtClassLoader: 加载java.ext.dirs下的class

AppClassLoader: 上级加载器是ExtClassloader, 加载java.class.path下的class

## 加载class的两种方式

隐式加载：通过类继承或者类引用，自动加载
显式加载：通过在代码中调用classLoader类进行加载具体的类

## class加载过程

1. findClass
2. Link Class （Verifying, Preparing, Resolving）
3. 类属性初始化赋值

Verifying: 字节码验证，类加载器对于类的字节码做格式上的校验
Preparing: 准备每个类定义中的字段、方法和实现接口所必需的数据结构
Resolving: 加载类所引用的其他所有的类，字段、方法签名

