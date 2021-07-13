package com.feng.shuaiqidemall.mapper;

import com.feng.shuaiqidemall.entity.Buyer;
import com.feng.shuaiqidemall.entity.Seller;

import java.util.List;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    List<Buyer> selectByName(String name);
}