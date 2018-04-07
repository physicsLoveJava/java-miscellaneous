
## 1. 什么是缓存行，伪共享又是什么意思？
缓存系统是以缓存行为单位进行存储，缓存行是2的整数幂个连续字节，一般32-256字节，最常见的缓存行是64个字节。
当多个线程修改互相独立的变量时，如果这些变量共享同一个缓存行，就会出现彼此影响彼此的性能，这也是伪共享的来源。

## 2. java如何避免伪共享？
在java6中，只需要保证单个类可以填满一整个缓存行就可以了，可以通过多个long字段进行填充
在java7中，则因为java内存优化的问题，无用的字段被移除，所以需要进行继承，才能保证不被优化
在java8中，可以使用-XX:-RestrictContented, 已经@Contended,让jvm自动填充。
举例：
```java
public static class A {
    int a;
    int b;
    @sun.misc.Contended  int c;
    int d;
}

public static class B extends A {
    int e;
}
```
com.lujian.casual.jol.Contented$B object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0    12        (object header)                           N/A
     12     4    int A.a                                       N/A
     16     4    int A.b                                       N/A
     20     4    int A.d                                       N/A
     24   128        (alignment/padding gap)                  
    152     4    int A.c                                       N/A
    156   128        (alignment/padding gap)                  
    284     4    int B.e                                       N/A
Instance size: 288 bytes
Space losses: 256 bytes internal + 0 bytes external = 256 bytes total


