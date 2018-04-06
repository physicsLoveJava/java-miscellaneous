
## 1. 为什么Object的equals和hashcode方法要保持一致性

hashCode要满足三个条件：
1. 在同一个应用程序中，同一个对象实例的hashCode必须保持一致
2. 如果两个对象等价，则两个的hashCode必须相同
3. 如果两个对象不等价，不需要两个的hashCode一定不相同，但是不相同可以提升hash表的性能

原因：
1. 两个等价的对象，如果一个放入hashMap中，同时用另一个去取，则会出现获不到的情况，
  * 两个对象hash到不同的桶中
  * 两个对象hash到相同的桶中，由于Map.Entry缓存了hash的值，则依然会取不到值
2. 对于HashSet而言，如果两个等价的对象，添加进set中，会导致不满足set的契约，只包含不重复的元素

## 2. IdentityHashMap的原理是什么，有什么应用场景？
