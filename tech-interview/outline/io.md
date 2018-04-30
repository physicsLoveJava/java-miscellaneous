
## NIO中的Buffer有哪些操作有什么含义？

首先Buffer是个抽象类的缓冲区，它主要有三个字段：
1. capacity表示缓冲区的容量，也就是缓冲区最大读写长度
2. limit表示缓冲区下一个不能读或者写的位置
3. position表示缓冲区下一个读或者写的位置

Buffer常用的操作有：
1. clear操作负责将一个缓冲区变成最开始的状态
```
public final Buffer clear() {
    position = 0;
    limit = capacity;
    mark = -1;
    return this;
}
```
2. flip操作，负责读写模式的转换，在读入一段数据后，可以通过该操作，将这部分数据写入其他数据源
3. rewind操作，将position变成0，整个Buffer缓冲区可以对外进行写入或者重新读取

