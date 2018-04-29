package designpatterns.future;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Client {

    public Data<String> get(String path) {
        RealData<String> realData = new RealData<>();
        FutureData<String> futureData = new FutureData<>(realData);
        new Thread(new HttpWorker(path, realData)).start();
        return futureData;
    }

    private class HttpWorker implements Runnable {

        private String path;

        private RealData<String> realData;

        public HttpWorker(String path, RealData<String> realData) {
            this.path = path;
            this.realData = realData;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                URLConnection conn = url.openConnection();
                InputStream stream = conn.getInputStream();
                ByteArrayOutputStream bis = new ByteArrayOutputStream(conn.getContentLength());
                int read;
                while((read = stream.read()) != -1) {
                    bis.write(read);
                }
                stream.close();
                byte[] bytes = bis.toByteArray();
                realData.setData(new String(bytes));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
