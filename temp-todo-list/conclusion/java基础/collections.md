
## java collections

### comparator
与相等保持一致性（c.compare(e1, e2) == 0 EQ e1.equals(e2)）
general contract for set （总体契约对于集合来说）
如果某个比较器不满足与相等保持一致性，则应该给出注释，说明此比较器不满足相等保持的一致性

如果针对于可以序列化的集合来说，Comparator也应该实现序列化的接口
比较器的compare方法应该保持等价关系，否则可能排序报错（jdk1.7与jdk1.6的兼容性问题）
http://www.oracle.com/technetwork/java/javase/compatibility-417013.html#incompatibilities

1. 自反性, sign(com(x, y)) == -sign(com(y, x))
2. 传递性, sign(com(x, y)) >= 0 && sign(com(y, z)) >= 0 ==> sign(com(x, z)) >=0
3. 对称性, sign(com(x, y)) == 0 => sign(com(x, z)) == sign(com(y, z))

### Comparable
comparable是实现该接口类的自然方法，如果和null进行比较，应该抛出NullPointerException，即使存在相等关系

相等关系和等价关系应该保持一致，否则对于SortSet与SortMap来说，可能会出现行为的怪异
如果存在某两个元素!e1.equals(e2) && c.compare(e1, e2) == 0, 则对于未指定Comparator的SortSet来说，
同样的添加两个元素，e1, e2, 第二个元素并不会被添加，因为对于SortSet而言，相等才是添加的标准，而等价并不是

### Iterable
能够让对象使用for-loop语句

