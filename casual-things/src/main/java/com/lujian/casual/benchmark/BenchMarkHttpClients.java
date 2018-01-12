package com.lujian.casual.benchmark;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BenchMarkHttpClients {

    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void getPageWithClose(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        BufferedReader reader = null;
        try {
            response = client.execute(httpGet);
            InputStream is = response.getEntity().getContent();
            String line;
            reader = new BufferedReader(new InputStreamReader(is));
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
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
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException("close client failed", e);
            }
        }
    }

    public static void getPage(String url) {
        HttpGet httpGet = new HttpGet(url);
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

    @Benchmark
    public void httpClientWithoutClose() {
        for (int i = 0; i < 6; i++) {
            int val = i % 3;
            switch (val) {
                case 0:
                    getPage("http://www.baidu.com");
                    break;
                case 1:
                    getPage("http://www.163.com");
                    break;
                case 2:
                    getPage("http://www.qq.com");
                    break;
            }
        }
    }

    @Benchmark
    public void httpClientClose() {
        for (int i = 0; i < 6; i++) {
            int val = i % 3;
            switch (val) {
                case 0:
                    getPageWithClose("http://www.baidu.com");
                    break;
                case 1:
                    getPageWithClose("http://www.163.com");
                    break;
                case 2:
                    getPageWithClose("http://www.qq.com");
                    break;
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMarkHttpClients.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(1)
                .threads(3)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
