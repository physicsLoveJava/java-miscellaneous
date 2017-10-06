package com.lujian.tech.libs;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientSamples {

    public static void main(String[] args) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        BufferedReader reader = null;
        try {
            response = client.execute(httpGet);
            InputStream is = response.getEntity().getContent();
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("close response stream failed", e);
                }
            }
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException("close response failed", e);
                }
            }
        }

    }

}
