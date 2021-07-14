package com.feng.shuaiqidemall.service.impl;

import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.mapper.UserInfoMapper;
import com.feng.shuaiqidemall.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AccountSecurityConfig security;

    @Autowired
    private CurrentService currentService;

    @Override
    public ResultDTO create(UserInfo userInfo) {
        List<UserInfo> mapList = userInfoMapper.selectByName(userInfo.getName());
        //判断是否已有该用户名
        if (mapList.size() > 0){
                return ResultDTO.failure("用户名已存在");
        }
        userInfo.setPassword(PasswordUtils.encode(userInfo.getPassword()));
        userInfoMapper.insert(userInfo);
        return ResultDTO.success("注册成功",userInfo);
    }

    @Override
    public ResultDTO login(UserInfo userInfo) {
        List<UserInfo> mapList = userInfoMapper.selectByName(userInfo.getName());

        if (mapList.isEmpty()){
            return ResultDTO.failure("不存在该用户");
        }
        if (!mapList.get(0).getRole().equals(userInfo.getRole())){
            return ResultDTO.failure(userInfo.getRole().equals("buyer") ? "请检查是否为卖家账号" : "请检查是否为买家账号");
        }
        if (PasswordUtils.matches(userInfo.getPassword(),mapList.get(0).getPassword())){
            String uuid = IdUtils.uuidWithDashes();//使用uuid作为令牌
            redisService.set(uuid,mapList.get(0),security.getExpTime());//插入缓存
            HttpServletResponse response = currentService.getHttpServletResponse();
            CookieUtils.setCookie(response,security.getTokenName(),uuid,security.getCookie().getMaxAge(),security.getCookie().getPath(),security.getCookie().getHttpOnly());
            return ResultDTO.success("登录成功",mapList.get(0));
        }
        return ResultDTO.failure("密码错误");
    }

    @Override
    public ResultDTO update(UserInfo userInfo) {
        //获得当前用户
        String uuid = currentService.getToken();
        UserInfo currentUser = redisService.get(uuid, UserInfo.class);
        //判断最新用户名是否存在
        List<UserInfo> mapList = userInfoMapper.selectByName(userInfo.getName());
        if (!mapList.get(0).getName().equals(currentUser.getName())&& mapList.size() > 0){
            return ResultDTO.failure("该用户名已经存在");
        }
        //更新操作
        userInfo.setPassword(PasswordUtils.encode(userInfo.getPassword()));
        userInfo.setId(currentUser.getId());
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i > 0) return ResultDTO.success("更新成功",userInfo);
        return ResultDTO.failure("更新失败");
    }


}
