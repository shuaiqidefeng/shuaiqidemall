package com.feng.shuaiqidemall.config;

import com.feng.shuaiqidemall.config.userInterceptor.BuyerInterceptor;
import com.feng.shuaiqidemall.config.userInterceptor.SellerInterceptor;
import com.feng.shuaiqidemall.config.userInterceptor.UserInterceptor;
import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HandlerInterceptor getUserInterceptor() {
        return new UserInterceptor(redisService, security, currentService);
    }

    @Bean
    public HandlerInterceptor getBuyerInterceptor() {
        return new BuyerInterceptor(currentService);
    }

    @Bean
    public HandlerInterceptor getSellerInterceptor() {
        return new SellerInterceptor(currentService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor())
                .addPathPatterns(security.getRootMapping())
                .excludePathPatterns(Arrays.asList(security.getAllowUri()));
        registry.addInterceptor(getBuyerInterceptor())
                .addPathPatterns("/buyer/**");
        registry.addInterceptor(getSellerInterceptor())
                .addPathPatterns("/seller/**");
        super.addInterceptors(registry);
    }
}
