package com.feng.shuaiqidemall.dto;

import com.feng.shuaiqidemall.entity.Good;

import java.util.List;

/**
 * 购物车中包含卖家名字和数量的商品
 */
public class CarSellerGoods {

    private int goodNumOnCar;
    private String sellerName;
    private Good good;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Good getGoods() {
        return good;
    }

    public void setGoods(Good good) {
        this.good = good;
    }

    public int getGoodId() {
        return goodNumOnCar;
    }

    public void setGoodId(int goodNumOnCar) {
        this.goodNumOnCar = goodNumOnCar;
    }

    @Override
    public String toString() {
        return "CarSellerGoods{" +
                "goodNumOnCar=" + goodNumOnCar +
                ", good=" + good +
                '}';
    }
}
