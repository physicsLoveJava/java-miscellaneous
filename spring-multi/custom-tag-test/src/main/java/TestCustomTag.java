import com.lujian.spring.multi.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCustomTag {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"context.xml"});
        Person abc = (Person) context.getBean("abc");
        System.out.println(abc);
    }

}
