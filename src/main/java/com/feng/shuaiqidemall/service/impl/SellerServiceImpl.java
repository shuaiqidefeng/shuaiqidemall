package com.feng.shuaiqidemall.service.impl;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.FirstTag;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.entity.SecondTag;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.mapper.FirstTagMapper;
import com.feng.shuaiqidemall.mapper.GoodMapper;
import com.feng.shuaiqidemall.mapper.SecondTagMapper;
import com.feng.shuaiqidemall.service.CurrentService;
import com.feng.shuaiqidemall.service.RedisService;
import com.feng.shuaiqidemall.service.SellerService;
import com.feng.shuaiqidemall.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private CurrentService currentService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TagService tagService;

    @Override
    public ResultDTO insertGood(Good good) {
        //获得当前用户
        String uuid = currentService.getToken();
        UserInfo currentUser = redisService.get(uuid, UserInfo.class);
        //向商品表增加商品
        if (currentUser.getRole().equals("buyer")) return ResultDTO.failure("你不是商家，无此权限");
        boolean isInsertSuccess = tagService.insertTag(good);
        if (!isInsertSuccess) return ResultDTO.failure("请正确也输入标签");
        good.setSellerId(currentUser.getId());
        int i = goodMapper.insert(good);
        if(i > 0) return ResultDTO.success("插入成功",good);
        return ResultDTO.failure("插入失败");
    }
}
