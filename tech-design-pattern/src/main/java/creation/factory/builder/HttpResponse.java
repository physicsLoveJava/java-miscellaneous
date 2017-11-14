package creation.factory.builder;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private boolean success;
    private String message;
    private Map<String, String> map;

    private HttpResponse() {}

    @Override
    public String toString() {
        return "HttpResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", map=" + map +
                '}';
    }

    interface ResponseBuilder {
        ResponseBuilder success(boolean success);
        ResponseBuilder message(String message);
        ResponseBuilder kv(String key, String value);
        HttpResponse build();
        String buildString();
    }

    protected static abstract class AbstractResponseBuilder implements ResponseBuilder{

        HttpResponse response;

        protected AbstractResponseBuilder() {
            response = new HttpResponse();
        }

        @Override
        public ResponseBuilder success(boolean success) {
            this.response.success = success;
            return this;
        }

        @Override
        public ResponseBuilder message(String message) {
            this.response.message = message;
            return this;
        }

        @Override
        public ResponseBuilder kv(String key, String value) {
            Map<String, String> map = this.response.map;
            if(map == null) {
                map = new HashMap<>();
            }
            map.put(key, value);
            this.response.map = map;
            return this;
        }

        @Override
        public HttpResponse build() {
            return this.response;
        }

    }

    static class JsonResponseBuilder extends AbstractResponseBuilder {

        public JsonResponseBuilder() {
            super();
        }

        @Override
        public String buildString() {
            return new Gson().toJson(this.response);
        }
    }

    static class XmlResponseBuilder extends AbstractResponseBuilder {

        public XmlResponseBuilder () {
            super();
        }

        @Override
        public String buildString() {
            XStream stream = new XStream();
            return stream.toXML(this.response);
        }
    }
}
