package com.feng.shuaiqidemall.config.userInterceptor;


import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SellerInterceptor implements HandlerInterceptor, ResponseBodyAdvice<Object> {

    private RedisService redisService;

    private AccountSecurityConfig security;

    private CurrentService currentService;

    public SellerInterceptor(CurrentService currentService) {
        this.currentService = currentService;
    }

    @Override
    // 处理Controller之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResultDTO dto = ResultDTO.failure("你不是卖家家，无此权限");
        //从Cookie中获取token
        if (!currentService.getCurrentUser().getRole().equals("seller")) {
            dto.setMessage("你不是卖家，无此权限");
            setFailure(response, dto);
            return false;
        }
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
