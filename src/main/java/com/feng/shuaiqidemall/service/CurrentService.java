package com.feng.shuaiqidemall.service;


import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.entity.Buyer;
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

    public Buyer getCurrentUser() {
        String token = getToken();
        return getUser(token);
    }

    public Buyer getUser(String token) {
        Buyer buyer = redisService.get(token, Buyer.class);
        if (Objects.isNull(buyer)) {
            throw new AccountException("无效token:" + token);
        }
        return buyer;
    }

    public HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

}
