package apache.lang;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import java.util.ArrayList;

public class ClassUtilsTest {

    @Test
    public void testClassUtils() {
        try {
            System.out.println(ClassUtils.getClass("apache.lang.ClassUtilsTest"));
            System.out.println(ClassUtils.getAbbreviatedName(ClassUtilsTest.class, 10));
            System.out.println(ClassUtils.getAllInterfaces(ArrayList.class));
            System.out.println(ClassUtils.getAllSuperclasses(ArrayList.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
