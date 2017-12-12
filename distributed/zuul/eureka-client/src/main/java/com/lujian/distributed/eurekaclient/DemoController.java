package com.lujian.distributed.eurekaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    DiscoveryClient client;

    @GetMapping("/demo")
    public String demo() {
        List<String> services = client.getServices();
        logger.debug("services: {}", services);
        System.out.println(services);
        return "demo";
    }

}
