package com.feng.shuaiqidemall;

import com.feng.shuaiqidemall.config.AccountSecurityCookieConfig;
import com.feng.shuaiqidemall.dto.CarSellerGoods;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.entity.ShoppingCar;
import com.feng.shuaiqidemall.mapper.FirstTagMapper;
import com.feng.shuaiqidemall.mapper.GoodMapper;
import com.feng.shuaiqidemall.mapper.ShoppingCarMapper;
import com.feng.shuaiqidemall.service.impl.BuyerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ShuaiqidemallApplicationTests {

    @Autowired
    AccountSecurityCookieConfig accountSecurityConfig;

    @Autowired
    FirstTagMapper firstTagMapper;

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    BuyerServiceImpl buyerService;

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Test
    void contextLoads() {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setBuyerId(111);
        shoppingCar.setGoodId(222);
        shoppingCar.setNum(1);
        shoppingCarMapper.insert(shoppingCar);
    }

    @Test
    void contextLoads1() {
        //取出购物车中的商品
        List<CarSellerGoods> goodsInCar = shoppingCarMapper.selectForSellerNameNumGood(12);
        HashMap<String, List<CarSellerGoods>> sortByname = new HashMap<>();
        //判断该用户的购物车是否为空
        if (goodsInCar.size() != 0){
            //不空则遍历结果集
            for (CarSellerGoods oneGood : goodsInCar) {
                //判断分类中是否有该卖家
                if (sortByname.containsKey(oneGood.getSellerName())){
                    //不空则取出list的引用并添加该商品进去
                    List<CarSellerGoods> goods = sortByname.get(oneGood.getSellerName());
                    goods.add(oneGood);
                }else {
                    //空则新建一个键值对
                    LinkedList<CarSellerGoods> goods = new LinkedList<>();
                    goods.add(oneGood);
                    sortByname.put(oneGood.getSellerName(),goods);
                }
            }
        }
        System.out.println(goodsInCar);
        System.out.println(sortByname);
    }

    @Test
    void contextLoads2() throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime = simpleDateFormat.format(date);
        Integer s = Integer.valueOf(currentTime);
        System.out.println(s);
    }

}
