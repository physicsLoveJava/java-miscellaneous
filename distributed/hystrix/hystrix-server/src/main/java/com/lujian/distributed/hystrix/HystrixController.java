package com.lujian.distributed.hystrix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixController {

    @Resource
    TestService testService;

    @GetMapping("/test.do")
    public String hystrix() {
        return testService.demoService();
    }

}
