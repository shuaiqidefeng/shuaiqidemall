package com.feng.shuaiqidemall.service;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.UserInfo;

public interface UserService {
    ResultDTO create(UserInfo userInfo);

    ResultDTO login(UserInfo userInfo);

    ResultDTO update(UserInfo userInfo);

    ResultDTO logout();
}
