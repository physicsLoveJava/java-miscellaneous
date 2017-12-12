package com.lujian.distributed.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {

    @Resource
    FeignService feignService;

    @GetMapping("/test")
    public String test() {
        return feignService.demo();
    }

}
