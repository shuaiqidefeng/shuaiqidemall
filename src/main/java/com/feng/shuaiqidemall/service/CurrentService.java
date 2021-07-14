package com.feng.shuaiqidemall.service;


import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.exception.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
public class CurrentService {

    @Autowired
    private AccountSecurityConfig security;

    @Autowired
    private RedisService redisService;

    public String getToken() {
        HttpServletRequest request = getHttpServletRequest();
        for (Cookie cookie : request.getCookies()) {
            if (Objects.equals(security.getTokenName(), cookie.getName())) {
                return cookie.getValue();
            }
        }
        throw new AccountException("请求中不存在token，请登录后使用");
    }

    public UserInfo getCurrentUser() {
        String token = getToken();
        return getUser(token);
    }

    public UserInfo getUser(String token) {
        Object user = redisService.get(token);
        if (Objects.isNull(user)) {
            throw new AccountException("无效token:" + token);
        }
        return (UserInfo) user;
    }

    public HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

}
