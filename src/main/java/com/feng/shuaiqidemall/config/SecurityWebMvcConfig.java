package com.feng.shuaiqidemall.config;

import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;

@Configuration
public class SecurityWebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AccountSecurityConfig security;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CurrentService currentService;

    @Bean
    @ConditionalOnMissingBean
    public HandlerInterceptor getUserInterceptor() {
        return new UserInterceptor(redisService, security, currentService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor())
                .addPathPatterns(security.getRootMapping())
                .excludePathPatterns(Arrays.asList(security.getAllowUri()));
        super.addInterceptors(registry);
    }
}
