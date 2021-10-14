package com.feng.shuaiqidemall.service;

import com.feng.shuaiqidemall.dto.ResultDTO;

public interface BuyerService {

    ResultDTO getTag();

    ResultDTO searchGood(String tag);

    ResultDTO addToCar(int goodId,int num);

    ResultDTO getGoodsFromShoppingCar();

    ResultDTO createOrder(String goodId,String num);

}
