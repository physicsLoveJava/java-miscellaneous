package com.lujian.ssoserver.service.impl;

import com.lujian.ssoserver.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    StringRedisTemplate template;

    @Override
    public void ping() {
        template.opsForList().leftPush("test", System.currentTimeMillis() + "");
    }

    @Override
    public boolean hasLogin(String id) {
        String val = template.opsForValue().get(id);
        return val != null && !val.equals("");
    }

    @Override
    public void login(String id) {
        template.opsForValue().set(id, id);
    }

    @Override
    public void logout(String id) {
        template.opsForValue().set(id, "");
    }

}
