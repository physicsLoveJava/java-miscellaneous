
## configserver
非持久配置中心（发布订阅中心）

流程：
client端发起post请求到vip
vip返回所有的configserver服务器地址列表给client
client随机选取一个地址，跟configserver建立链接，并通过定时发送心跳包，保持链接
client向configserver发起服务订阅请求
configserver推送服务提供者的地址列表给client

## 特性
1. 提供非持久数据的实时发布、订阅
dataId + groupId

2. 提供数据自动聚合功能
将同一个组内的数据聚集，同一个hsf服务的多台机器，对于客户端来说都是可见的

3. 非集中式的软负载产品
4. 支持请求级别的负载均衡
5. 集群支持多点写，多点读
每个客户端，通过dataId + groupId区别
6. 支持地址权重动态调整

## zk区别
configserver主要是高可用的服务发现，机器挂了一部分，不影响大部分的使用(ap)
zk则是分布式协调，保证分布式对于某个决策的一致性（cp)

