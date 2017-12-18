package com.lujian.ssoserver.service.impl;

import com.lujian.ssoserver.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    StringRedisTemplate template;

    @Resource
    SessionRepository sessionRepository;

    @Override
    public void ping(String id) {
        Session session = sessionRepository.getSession(id);
        if(session == null) {
            System.out.println("session is null");
        }else {
            System.out.println(session.getAttribute("loginBy"));
        }
    }

    @Override
    public boolean hasLogin(String id) {
        Session session = sessionRepository.getSession(id);
        return session != null;
    }

    @Override
    public void login(String id) {
        Session session = sessionRepository.getSession(id);
        if(session == null) {
            session = sessionRepository.createSession();
        }
        session.setAttribute("loginBy", "8083");
        sessionRepository.save(session);
    }

    @Override
    public void logout(String id) {
        sessionRepository.delete(id);
    }

}
