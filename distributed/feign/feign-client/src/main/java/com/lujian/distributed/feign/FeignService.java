package com.lujian.distributed.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-client-service")
public interface FeignService {

    @RequestMapping("demo")
    String demo();

}
