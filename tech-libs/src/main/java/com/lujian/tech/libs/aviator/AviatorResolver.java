package com.lujian.tech.libs.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AviatorResolver {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        getContext();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).get();
        }
    }

    private static void getContext() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("context", new Context());
        map.put("config", new Config());
        for (int i = 0; i < 100000000; i++) {
            String exp = "context.a * config.a + " + new Random().nextInt(1000000000);
            Object result = AviatorEvaluator.execute(exp, map);
            System.out.println(result);
        }
    }

    public static class Context {
        double a = 0.01;

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }
    }

    public static class Config {
        double a = 0.01;

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }
    }

}
