package com.feng.shuaiqidemall.config;


import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class UserInterceptor implements HandlerInterceptor, ResponseBodyAdvice<Object> {

    private RedisService redisService;

    private AccountSecurityConfig security;

    private CurrentService currentService;

    public UserInterceptor(RedisService redisService, AccountSecurityConfig security, CurrentService currentService) {
        this.redisService = redisService;
        this.security = security;
        this.currentService = currentService;
    }

    @Override
    // 处理Controller之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResultDTO dto = ResultDTO.failure("token不存在，请登录后使用");
        //从Cookie中获取token
        if (Objects.isNull(request.getCookies())) {
            dto.setMessage("token不存在，请登录后使用");
            setFailure(response, dto);
            return false;
        }
        Optional<Cookie> optional = Arrays.stream(request.getCookies()).filter(cookie -> Objects.equals(security.getTokenName(), cookie.getName())).findFirst();
        if (!optional.isPresent()) {
            dto.setMessage("token不存在，请登录后使用");
            setFailure(response, dto);
            return false;
        }
        String token = optional.get().getValue();
        //拼接出Redis的key
        String key = token;
        //查找缓存中
        UserInfo userInfo = redisService.get(token, UserInfo.class);
        if (Objects.isNull(userInfo)) {
            dto.setMessage("无效的token，请重新登录");
            setFailure(response, dto);
            return false;
        }
        redisService.set(key, userInfo, security.getExpTime());//刷新缓存时间
        return true;
    }

    /**
     * 此方法是拦截返回值,并且操作返回值的,Controller之后的处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (Objects.nonNull(body) && body instanceof ResultDTO) {
            if (Objects.equals(security.getUsingDto(), Boolean.FALSE)) {
                HttpServletResponse response = currentService.getHttpServletResponse();
                ResultDTO dto = (ResultDTO) body;
            }
        }
        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }




    private void setFailure(HttpServletResponse response, ResultDTO dto) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(dto.toString());
    }
}
