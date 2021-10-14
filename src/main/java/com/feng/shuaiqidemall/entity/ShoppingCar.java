package com.feng.shuaiqidemall.entity;

public class ShoppingCar {
    private Integer id;

    private Integer buyerId;

    private Integer goodId;

    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", goodId=" + goodId +
                ", num=" + num +
                '}';
    }
}