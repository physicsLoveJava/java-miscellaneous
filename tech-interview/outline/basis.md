
## 1. java的基本类型有哪些？
boolean, byte, char, short, int, long, float, double
引用类型

## 2. float f = 3.4是否正确？
需要转型，或者进行浮点字面量表示

## 3.short s1 = 1; s1 = s1 + 1;有错吗?short s1 = 1; s1 += 1;有错吗？
对于short s1 = 1; s1 = s1 + 1;由于1是int类型，因此s1+1运算结果也是int 型，需要强制转换类型才能赋值给short型。
而short s1 = 1; s1 += 1;可以正确编译，因为s1+= 1;相当于s1 = (short)(s1 + 1);其中有隐含的强制类型转换。

## 4. java中int与Integer的转化
Integer a = 3 ==> Integer a = Integer.valueOf(3); 存在IntegerCache的问题，[-128, 127]

## 5. switch 是否能作用在byte 上，是否能作用在long 上，是否能作用在String上？
在Java 5以前，switch(expr)中，expr只能是byte、short、char、int。
从Java 5开始，Java中引入了枚举类型，expr也可以是enum类型，
从Java 7开始，expr还可以是字符串（String），但是长整型（long）在目前所有的版本中都是不可以的。

## 6.两个对象值相同(x.equals(y) == true)，但却可有不同的hash code，这句话对不对？
不对，如果两个对象x和y满足x.equals(y) == true，它们的哈希码（hash code）应当相同。
Java对于eqauls方法和hashCode方法是这样规定的：(1)如果两个对象相同（equals方法返回true），那么它们的hashCode值一定要相同；(2)如果两个对象的hashCode相同，它们并不一定相同。
当然，你未必要按照要求去做，但是如果你违背了上述原则就会发现在使用容器时，
相同的对象可以出现在Set集合中，同时增加新元素的效率会大大下降（对于使用哈希存储的系统，如果哈希码频繁的冲突将会造成存取性能急剧下降）。

## 7.当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递？
是值传递。Java语言的方法调用只支持参数的值传递。当一个对象实例作为一个参数被传递到方法中时，参数的值就是对该对象的引用。
对象的属性可以在被调用过程中被改变，但对对象引用的改变是不会影响到调用者的。
C++和C#中可以通过传引用或传输出参数来改变传入的参数的值。在C#中可以编写如下所示的代码，但是在Java中却做不到。

