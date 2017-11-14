package creation.factory.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpResponseTest {

    @Test
    public void testJson() {
        String response = new HttpResponse.JsonResponseBuilder()
                .success(true).message("aaa")
                .kv("a", "a").kv("b", "b")
                .kv("c", "c").kv("d", "d").buildString();
       assertEquals("{\"success\":true,\"message\":\"aaa\",\"map\":{\"d\":\"d\",\"b\":\"b\",\"c\":\"c\",\"a\":\"a\"}}",
               response);
    }

    @Test
    public void testXml() {
        String response = new HttpResponse.XmlResponseBuilder()
                .success(true).message("aaa")
                .kv("a", "a").kv("b", "b")
                .kv("c", "c").kv("d", "d").buildString();
        assertEquals("<creation.factory.builder.HttpResponse>\n" +
                        "  <success>true</success>\n" +
                        "  <message>aaa</message>\n" +
                        "  <map>\n" +
                        "    <entry>\n" +
                        "      <string>d</string>\n" +
                        "      <string>d</string>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "      <string>b</string>\n" +
                        "      <string>b</string>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "      <string>c</string>\n" +
                        "      <string>c</string>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "      <string>a</string>\n" +
                        "      <string>a</string>\n" +
                        "    </entry>\n" +
                        "  </map>\n" +
                        "</creation.factory.builder.HttpResponse>",
                response);
    }
}