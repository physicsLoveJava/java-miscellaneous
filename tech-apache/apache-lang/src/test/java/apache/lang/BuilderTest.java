package apache.lang;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

public class BuilderTest {

    @Test
    public void testToStringBuilder() {
        System.out.println(ToStringBuilder.reflectionToString("aa"));
        System.out.println(ReflectionToStringBuilder.toString("aa"));
    }

}
