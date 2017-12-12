package com.lujian.distributed.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class TestService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String demoService() {
        return restTemplate.getForEntity("http://EUREKA-CLIENT-SERVICE/demo", String.class).getBody();
    }


    public String fallback() {
        return "error";
    }
}
