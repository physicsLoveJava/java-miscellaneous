package com.lujian.ssoserver.service;

public interface RedisService {

    void ping(String id);

    boolean hasLogin(String id);

    void login(String id);

    void logout(String id);
}
