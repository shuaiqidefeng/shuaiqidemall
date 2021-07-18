package com.feng.shuaiqidemall.service;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Good;
import org.springframework.stereotype.Service;

public interface SellerService {
    ResultDTO insertGood(Good good);
}
