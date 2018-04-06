package concurrent.basic;

import java.util.WeakHashMap;

public class WeakReferenceWithThreadLocalLike {

    private static WeakHashMap<ThreadLocalLike, String> relationMap = new WeakHashMap<>();

    static class LocalVariableHolder {
        ThreadLocalLike like;
    }

    static class ThreadLocalLike {

        public void set(String obj) {
            relationMap.put(this, obj);
        }

        public void remove() {
            relationMap.remove(this);
        }
    }

    public static void main(String[] args) {
        LocalVariableHolder holder = new LocalVariableHolder();
        holder.like = new ThreadLocalLike();
        holder.like.set("abc");

        //hold the key
        System.out.println(relationMap);
        System.gc();//holder like has ref
        System.out.println(relationMap);
        holder.like = new ThreadLocalLike();
        holder.like.set("1232234");
        System.out.println(relationMap);
        System.gc();//holder has no ref, so gc handles it
        System.out.println(relationMap);
    }

}
