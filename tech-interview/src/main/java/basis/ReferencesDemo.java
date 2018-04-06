package basis;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

public class ReferencesDemo {

    static class BigObject {
        byte[] bytes = new byte[1024 * 1024];

        /**
         * finalize can't do anything to avoid the out of memory
         * @throws Throwable
         */
//        @Override
//        protected void finalize() throws Throwable {
//            System.out.println("to be killed");
//            System.out.println(bytes.length);
//            super.finalize();
//        }
    }

    /**
     * -verbose:gc -Xms10m -Xmx10m
     */
    static class Test1 {
        public static void main(String[] args) throws InterruptedException {
            ReferenceQueue<BigObject> rq = new ReferenceQueue<>();
            List<SoftReference<BigObject>> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add(new SoftReference<>(new BigObject(), rq));
            }
            System.out.println("before");
            for (SoftReference<BigObject> reference : list) {
                System.out.println(reference.get());
            }
            System.gc();
            System.out.println("after");
            for (SoftReference<BigObject> reference : list) {
                System.out.println(reference.get());
            }
            for (int i = 0; i < 5; i++) {
                list.add(new SoftReference<>(new BigObject(), rq));
            }
            System.gc();
            System.out.println("after memory failed to allocation");
            for (SoftReference<BigObject> reference : list) {
                System.out.println(reference.get());
            }
            System.out.println("reference queue");
            Reference<? extends BigObject> ref;
            while((ref = rq.poll()) != null) {
                System.out.println(ref.get());
            }
        }
    }

    static class Test2 {
        public static void main(String[] args) {
            ReferenceQueue<BigObject> rq = new ReferenceQueue<>();
            BigObject obj = new BigObject();
//            WeakReference<BigObject> reference = new WeakReference<>(new BigObject(), rq);
            WeakReference<BigObject> reference = new WeakReference<>(obj, rq);//如果该引用本身是强引用，则无法通过弱引用消除，除非该引用的对象消失
            System.out.println(reference.get());
            System.gc();
            System.out.println(reference.get());
            System.out.println("reference queue");
            Reference<? extends BigObject> ref = null;
            while((ref = rq.poll()) != null) {
                System.out.println(ref.get());
            }
        }
    }

    static class Test3 {
        public static void main(String[] args) {
            ReferenceQueue<BigObject> rq = new ReferenceQueue<>();
            PhantomReference<BigObject> reference = new PhantomReference<>(new BigObject(), rq);
            System.out.println(reference.get());
            System.gc();
            System.out.println(reference.get());
            System.out.println("reference queue");
            Reference<? extends BigObject> ref = null;
            while((ref = rq.poll()) != null) {
                System.out.println(ref.get());
            }
        }
    }

    static class WeakObject extends WeakReference<BigObject> {

        private BigObject external;

        public WeakObject(BigObject referent, BigObject external) {
            super(referent);
            this.external = external;
        }
    }

    static class Test4 {
        public static void main(String[] args) {
//            WeakObject obj = new WeakObject(new BigObject(), new BigObject());
//            BigObject p = new BigObject();
//            WeakObject obj = new WeakObject(p, new BigObject());
            BigObject p = new BigObject();
            BigObject q = new BigObject();
            WeakObject obj = new WeakObject(p, q);
            p = null;
            System.out.println(obj.get());
            System.gc();
            System.out.println(obj.get());
            System.out.println(q);
        }
    }
}
