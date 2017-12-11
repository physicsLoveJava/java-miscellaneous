package com.lujian.distributed.eurekaconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    RestTemplate template;

    @GetMapping("/consumer")
    public String consumer() {
        return template.getForEntity("http://EUREKA-CLIENT-SERVICE/demo", String.class).getBody();
    }

}
