package typical.primitives;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeUsage {

    private int value;

    private static Unsafe unsafe = null;

    private static long valueOffset;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
            valueOffset = UnSafeUsage.unsafe.objectFieldOffset(UnSafeUsage.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getValue (UnSafeUsage usage) {
        return unsafe.getInt(usage, valueOffset);
    }

    public static void setValue(UnSafeUsage usage, int newValue) {
        unsafe.putInt(usage, valueOffset, newValue);
    }

    public static void random(UnSafeUsage usage) {
        unsafe.setMemory(usage, valueOffset, 4, (byte) 1);
    }

    public static void main(String[] args) {
        UnSafeUsage usage = new UnSafeUsage();
        System.out.println(UnSafeUsage.getValue(usage));
        UnSafeUsage.setValue(usage, 10);
        System.out.println(UnSafeUsage.getValue(usage));
        UnSafeUsage.random(usage);
        System.out.println(UnSafeUsage.getValue(usage));
    }

}
