
## Lock
相比synchronized支持更加灵活结构，需要注意的是，对于异常情况的解锁

提供了三种情况的锁获取：
1. non-interruptible
2. interruptible
3. timed

## Condition
条件变量可以通过等待与唤醒进行线程顺序的控制
code example:
<pre>
 class BoundedBuffer {
   <b>final Lock lock = new ReentrantLock();</b>
   final Condition notFull  = <b>lock.newCondition(); </b>
   final Condition notEmpty = <b>lock.newCondition(); </b>

   final Object[] items = new Object[100];
   int putptr, takeptr, count;

   public void put(Object x) throws InterruptedException {
     <b>lock.lock();
     try {</b>
       while (count == items.length)
         <b>notFull.await();</b>
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       <b>notEmpty.signal();</b>
     <b>} finally {
       lock.unlock();
     }</b>
   }

   public Object take() throws InterruptedException {
     <b>lock.lock();
     try {</b>
       while (count == 0)
         <b>notEmpty.await();</b>
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       <b>notFull.signal();</b>
       return x;
     <b>} finally {
       lock.unlock();
     }</b>
   }
 }
 </pre>
 
 