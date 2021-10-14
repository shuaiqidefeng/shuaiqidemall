package com.feng.shuaiqidemall.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Good {
    private Integer id;

    private String name;

    private String detail;

    private String tag;

    private BigDecimal price;

    private Integer number;

    private String picAddress;

    private Integer sellerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress == null ? null : picAddress.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", tag='" + tag + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", picAddress='" + picAddress + '\'' +
                ", sellerId=" + sellerId +
                '}';
    }

    //用于求交集
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return Objects.equals(id, good.id) && Objects.equals(name, good.name) && Objects.equals(detail, good.detail) && Objects.equals(tag, good.tag) && Objects.equals(price, good.price) && Objects.equals(number, good.number) && Objects.equals(picAddress, good.picAddress) && Objects.equals(sellerId, good.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, detail, tag, price, number, picAddress, sellerId);
    }

}