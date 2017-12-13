package com.lujian.distributed.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DemoController {

    @Value("${s}")
    private String dev;

    @GetMapping("/dev")
    public String dev() {
        return this.dev;
    }

}
