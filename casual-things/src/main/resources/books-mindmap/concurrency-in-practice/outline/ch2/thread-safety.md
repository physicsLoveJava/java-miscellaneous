
##线程安全性
程序内部遵守一定的不变形条件，以及程序在执行的后置条件。

无状态的线程安全特性
无状态，程序内部没有任何域，同时也没有引用其他类中的域。

##原子性

###竞争条件
当某个程序的执行结果取决于不同线程执行的顺序时，就会出现竞争条件（Race Condition)

###加锁
java内置锁，监视锁，是线程互斥的以及可重入的。
通过加锁来保证操作的**原子性**


