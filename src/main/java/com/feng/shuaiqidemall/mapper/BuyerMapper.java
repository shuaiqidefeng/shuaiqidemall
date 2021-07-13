package com.feng.shuaiqidemall.mapper;

import com.feng.shuaiqidemall.entity.Buyer;

import java.util.List;

public interface BuyerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Buyer record);

    int insertSelective(Buyer record);

    Buyer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Buyer record);

    int updateByPrimaryKey(Buyer record);

    List<Buyer> selectByName(String name);
}