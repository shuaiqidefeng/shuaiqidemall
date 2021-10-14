package com.feng.shuaiqidemall.mapper;

import com.feng.shuaiqidemall.dto.CarSellerGoods;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.entity.ShoppingCar;

import java.util.List;

public interface ShoppingCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCar record);

    int insertSelective(ShoppingCar record);

    ShoppingCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShoppingCar record);

    int updateByPrimaryKey(ShoppingCar record);

    List<ShoppingCar> selectByBuyerAndGood(ShoppingCar shoppingCar);

    List<ShoppingCar> selectByBuyerID(int buyerId);

    List<CarSellerGoods> selectForSellerNameNumGood(int buyerId);

    List<Good> test1();

}