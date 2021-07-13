package com.feng.shuaiqidemall.service.impl;

import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Buyer;
import com.feng.shuaiqidemall.entity.Seller;
import com.feng.shuaiqidemall.entity.User;
import com.feng.shuaiqidemall.mapper.BuyerMapper;
import com.feng.shuaiqidemall.mapper.SellerMapper;
import com.feng.shuaiqidemall.service.BuyerService;
import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import com.feng.shuaiqidemall.utils.CookieUtils;
import com.feng.shuaiqidemall.utils.IdUtils;
import com.feng.shuaiqidemall.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AccountSecurityConfig security;

    @Autowired
    private CurrentService currentService;

    @Override
    public <E extends User> ResultDTO create(E e) {
        List<E> mapList = null;
        if (!e.getClass().equals(Buyer.class) && !e.getClass().equals(Seller.class)){
            throw new RuntimeException("传入参数有误");
        }else if (e.getClass().equals(Buyer.class)){
            mapList = (List<E>) buyerMapper.selectByName(e.getName());
        }else if (e.getClass().equals(Seller.class)){
            mapList = (List<E>) sellerMapper.selectByName(e.getName());
        }

        if (mapList.size() > 0){
            return ResultDTO.failure("用户名已存在");
        }
        e.setPassword(PasswordUtils.encode(e.getPassword()));
        if (e.getClass().equals(Buyer.class)){
            Buyer buyer = (Buyer)e;
            buyerMapper.insert(buyer);
        }else if (e.getClass().equals(Seller.class)){
            Seller seller = (Seller)e;
            sellerMapper.insert(seller);
        }
        return ResultDTO.success("注册成功",e);
    }

    @Override
    public <E extends User> ResultDTO login(E e) {
        List<E> mapList = null;
        if (!e.getClass().equals(Buyer.class) && !e.getClass().equals(Seller.class)){
            throw new RuntimeException("传入参数有误");
        }else if (e.getClass().equals(Buyer.class)){
            mapList = (List<E>) buyerMapper.selectByName(e.getName());
        }else if (e.getClass().equals(Seller.class)){
            mapList = (List<E>) sellerMapper.selectByName(e.getName());
        }
        if (mapList.isEmpty()){
            return ResultDTO.failure("不存在该用户");
        }
        if (PasswordUtils.matches(e.getPassword(),mapList.get(0).getPassword())){
            String uuid = IdUtils.uuidWithDashes();//使用uuid作为令牌
            redisService.set(uuid,mapList.get(0),security.getExpTime());//插入缓存
            HttpServletResponse response = currentService.getHttpServletResponse();
            CookieUtils.setCookie(response,security.getTokenName(),uuid,security.getCookie().getMaxAge(),security.getCookie().getPath(),security.getCookie().getHttpOnly());
            return ResultDTO.success("登录成功",mapList.get(0));
        }
        return ResultDTO.failure("密码错误");
    }
}
