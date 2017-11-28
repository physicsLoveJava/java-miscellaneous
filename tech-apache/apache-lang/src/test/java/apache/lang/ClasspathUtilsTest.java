package apache.lang;

import org.apache.commons.lang3.ClassPathUtils;
import org.junit.Test;

public class ClasspathUtilsTest {

    @Test
    public void testClasspath() {
        ClassLoader cl = getClass().getClassLoader();
        while(cl != null) {
            System.out.println(cl.getClass() + " : " + ClassPathUtils.toFullyQualifiedName(cl.getClass(), "a.properties"));
            cl = cl.getParent();
        }
    }

}
