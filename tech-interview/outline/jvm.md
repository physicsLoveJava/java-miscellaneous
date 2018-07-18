
## 1.jvm类加载机制
虚拟机把class文件加载到内存，并对数据进行校验、转换解析和初始化，形成被虚拟机直接使用的java类型。
* 类型的加载、连接和初始化都是在运行期完成，这种策略令类加载增加性能开销，但是为java应用程序提供高度的灵活性。

1. 加载
2. 连接: 验证，准备，解析
3. 初始化
4. 使用
5. 卸载

### 加载
1. 通过类的全限定名来获取此类的二进制字节流
2. 将这个字节流代表的静态存储结构转化为方法区的运行时数据结构
3. 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口

举例：
1. jar, war, ear
2. applet
3. JDKProxy, ProxyGenerator.generateProxyClass --> $Proxy代理类
4. jsp --> servlet class

### 连接
#### 验证
确保Class文件中的字节流包含的信息符合当前虚拟机的规范，不会危害虚拟机自身的安全

1. 文件格式验证
2. 元数据验证
3. 字节码验证
4. 符号引用验证

1. 文件格式验证
* 魔数
* 主次版本号
* 常量池中的常量
* 常量索引是否存在
* 常量中是否有不符合UTF8编码的信息

2. 元数据验证
* 是否有父类
* 是否继承不被允许继承的类（final)
* 类是否实现该实现的方法
* 类中字段、方法的检查

3. 字节码验证
* 保证任意时刻操作数栈的数据类型与指令代码序列都能配合工作
* 保证跳转指令不会跳转到方法体以外的字节码指令上
* 保证方法体中的类型转换是有效的

4. 符号引用验证
* 符号引用中通过字符串描述能否找到对应的类
* 在类中能否找到对应的方法与字段（方法描述符）
* 符号引用中的类，字段，方法是否可以被当前类访问

#### 准备
准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些变量所使用的内存都将在方法区中进行分配。
类变量而非实例变量，至于初始默认值，则交由类初始化去完成

#### 解析
解析阶段就是虚拟机将常量池中的符号引用替换为直接引用的过程

* 符号引用：符号引用用一组符号来描述引用目标。引用目标不一定已经加载到内存中。
* 直接引用：直接引用可以是直接指向目标的指针、或者间接引用的句柄，引用目标已经加载到内存中。

在anewarray,invoke*,get*等字节码使用前，需要对操作的符号引用进行解析，而常量的符号引用则根据实现有所不同

### 初始化
类初始化阶段是类加载过程的最后一步

* <clinit>()方法是编译器自动收集类中的所有类变量的复制动作和静态语句块的语句合并产生，顺序由语句的定义的顺序决定，
同时子类的<clinit>()方法执行前，需要保证其父类的类初始化方法已经执行完毕。
* 虚拟机内部会正确地对类初始化进行正确的同步，保证每个类的初始化只执行一次。

### 类加载器
类加载器的名称空间，即同一个类加载器下的同一个类是像等价的。

#### 双亲委派模型
启动类加载器与其他类加载器

1. Bootstrap : java \lib下的类
2. Extension : java \lib\ext下的类
3. Application: 负责加载用户类路径上的类库

工作过程： 如果一个类加载器收到类加载请求，他不会自己加载，而是把这个请求委派给父加载器进行完成。

JNDI的SPI拓展使用ThreadContextClassLoader,加载具体细节的代码
主要是为了解决顶层加载器加载下层类的方法
1. tomcat中类加载机制
2. osgi类加载机制
3. 热替换如何实现

### jvm启动流程
1. java xxx.class
2. 装载配置jvm.cfg
3. 根据配置寻找jvm.dll
4. 初始化jvm,获取JNIEnv接口
5. 找到main方法并运行

### jvm基本结构
见jvm-structure.jpg

1. Class文件
2. 类加载器子系统
3. 内存空间
    1. 方法区
    2. heap
    3. stack
    4. native stack
4. 垃圾收集器
5. pc寄存器
6. 执行引擎
7. 本地方法接口
8. 本地方法库


栈上分配 将对象分配在栈中，函数调用结束后自动清理

### jmm模型
1. 单线程执行结果不变，指令可能发生重排，多线程的整体是无序的
2. happens-before原则

### 解释执行和运行运行（JIT）
1. 解释执行以解释方式运行字节码

1. 将字节码变异成机器码
2. 直接执行机器码
3. 运行时进行编译
4. 编译后的性能有很大的提升

### jvm参数
gc:
-verbose:gc
-XX:+PrintGC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:log/xx.log
-XX:+PrintHeapAtGC

trace:
-XX:+TracingClassLoading
-XX:+PrintClassHistogram

heap结构
young
eden s0 s1 tenured

-Xmx 最大堆
-Xms 最小堆
-Xmn 新生代
-XX:NewRatio 新生代和老年代的比 ：4 --> 新生代：老年代 = 1 ： 4
-XX:SurvivorRatio 两个survivor和eden区的比例： 8 --> 2 * survivor : eden = 2 : 8

-XX:+HeapDumpOnOutOfMemoryError
-XX:+HeapDumpPath

-XX:MaxPermSize
-XX:PermSize
-Xss 栈大小

### 系统情况监控
linux:
uptime
top
vmstat
pidstat (apt-get install sysstat)

window:
perfmon
pslist

### 内存分析
1. 对象浅堆
和对象内容无关，只是对象的结构

2. 对象深堆
对象垃圾回收后释放的内存大小

### java反射原理实现

*getDeclaredMethod*
```cfml
public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
    throws NoSuchMethodException, SecurityException {
    checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
    Method method = searchMethods(privateGetDeclaredMethods(false), name, parameterTypes);
    if (method == null) {
        throw new NoSuchMethodException(getName() + "." + name + argumentTypesToString(parameterTypes));
    }
    return method;
}
```

privateGetDeclaredMethods方法从缓存或JVM中获取该Class中申明的方法列表，
searchMethods方法将从返回的方法列表里找到一个匹配名称和参数的方法对象,通过ReflectionFactory.copyMethod复制方法

```cfml
Method copy() {
    // This routine enables sharing of MethodAccessor objects
    // among Method objects which refer to the same underlying
    // method in the VM. (All of this contortion is only necessary
    // because of the "accessibility" bit in AccessibleObject,
    // which implicitly requires that new java.lang.reflect
    // objects be fabricated for each reflective call on Class
    // objects.)
    if (this.root != null)
        throw new IllegalArgumentException("Can not copy a non-root Method");

    Method res = new Method(clazz, name, parameterTypes, returnType,
                            exceptionTypes, modifiers, slot, signature,
                            annotations, parameterAnnotations, annotationDefault);
    res.root = this;
    // Might as well eagerly propagate this if already present
    res.methodAccessor = methodAccessor;
    return res;
}
```

所次每次调用getDeclaredMethod方法返回的Method对象其实都是一个新的对象，且新对象的root属性都指向原来的Method对象，如果需要频繁调用，最好把Method对象缓存起来

```c 
 // reflection data that might get invalidated when JVM TI RedefineClasses() is called
private static class ReflectionData<T> {
    volatile Field[] declaredFields;
    volatile Field[] publicFields;
    volatile Method[] declaredMethods;
    volatile Method[] publicMethods;
    volatile Constructor<T>[] declaredConstructors;
    volatile Constructor<T>[] publicConstructors;
    // Intermediate results for getFields and getMethods
    volatile Field[] declaredPublicFields;
    volatile Method[] declaredPublicMethods;
    volatile Class<?>[] interfaces;

    // Value of classRedefinedCount when we created this ReflectionData instance
    final int redefinedCount;

    ReflectionData(int redefinedCount) {
        this.redefinedCount = redefinedCount;
    }
}

private volatile transient SoftReference<ReflectionData<T>> reflectionData;
```

缓存反射对象的数据结构：reflectionData,是一个软引用类型的对象，
在内存不够的情况会被回收，这个时候反射就会重新创建该对象

*Method.invoke*
```c 
public Object invoke(Object obj, Object... args)
        throws IllegalAccessException, IllegalArgumentException,
           InvocationTargetException
{
    if (!override) {
        if (!Reflection.quickCheckMemberAccess(clazz, modifiers)) {
            Class<?> caller = Reflection.getCallerClass();
            checkAccess(caller, clazz, obj, modifiers);
        }
    }
    MethodAccessor ma = methodAccessor;             // read volatile
    if (ma == null) {
        ma = acquireMethodAccessor();
    }
    return ma.invoke(obj, args);
}

public MethodAccessor newMethodAccessor(Method var1) {
    checkInitted();
    if (noInflation && !ReflectUtil.isVMAnonymousClass(var1.getDeclaringClass())) {
        return (new MethodAccessorGenerator()).generateMethod(var1.getDeclaringClass(), var1.getName(), var1.getParameterTypes(), var1.getReturnType(), var1.getExceptionTypes(), var1.getModifiers());
    } else {
        NativeMethodAccessorImpl var2 = new NativeMethodAccessorImpl(var1);
        DelegatingMethodAccessorImpl var3 = new DelegatingMethodAccessorImpl(var2);
        var2.setParent(var3);
        return var3;
    }
}

public Object invoke(Object var1, Object[] var2) throws IllegalArgumentException, InvocationTargetException {
    if (++this.numInvocations > ReflectionFactory.inflationThreshold() && !ReflectUtil.isVMAnonymousClass(this.method.getDeclaringClass())) {
        MethodAccessorImpl var3 = (MethodAccessorImpl)(new MethodAccessorGenerator()).generateMethod(this.method.getDeclaringClass(), this.method.getName(), this.method.getParameterTypes(), this.method.getReturnType(), this.method.getExceptionTypes(), this.method.getModifiers());
        this.parent.setDelegate(var3);
    }

    return invoke0(this.method, var1, var2);
}

//MethodAccessGenerator --> generateMethod
return (MagicAccessorImpl)AccessController.doPrivileged(new PrivilegedAction<MagicAccessorImpl>() {
        public MagicAccessorImpl run() {
            try {
                return (MagicAccessorImpl)ClassDefiner.defineClass(var13, var17, 0, var17.length, var1.getClassLoader()).newInstance();
            } catch (IllegalAccessException | InstantiationException var2) {
                throw new InternalError(var2);
            }
        }
    });
    
    
/** <P> We define generated code into a new class loader which
      delegates to the defining loader of the target class. It is
      necessary for the VM to be able to resolve references to the
      target class from the generated bytecodes, which could not occur
      if the generated code was loaded into the bootstrap class
      loader. </P>

      <P> There are two primary reasons for creating a new loader
      instead of defining these bytecodes directly into the defining
      loader of the target class: first, it avoids any possible
      security risk of having these bytecodes in the same loader.
      Second, it allows the generated bytecodes to be unloaded earlier
      than would otherwise be possible, decreasing run-time
      footprint. </P>
    */    
static Class<?> defineClass(String var0, byte[] var1, int var2, int var3, final ClassLoader var4) {
    ClassLoader var5 = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
        public ClassLoader run() {
            return new DelegatingClassLoader(var4);
        }
    });
    return unsafe.defineClass(var0, var1, var2, var3, var5, (ProtectionDomain)null);
}
```

这里每次都生成新的类加载器，是为了性能考虑，在某些情况下可以卸载这些生成的类，
因为类的卸载是只有在类加载器可以被回收的情况下才会被回收的，
如果用了原来的类加载器，那可能导致这些新创建的类一直无法被卸载，
从其设计来看本身就不希望这些类一直存在内存里的，在需要的时候有就行了。











