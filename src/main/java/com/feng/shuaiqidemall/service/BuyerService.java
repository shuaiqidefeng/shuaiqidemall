package com.feng.shuaiqidemall.service;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Buyer;
import com.feng.shuaiqidemall.entity.User;

public interface BuyerService {
    <E extends User>ResultDTO create(E e);

    <E extends User> ResultDTO login(E e);
}
