package com.feng.shuaiqidemall.service.impl;

import com.feng.shuaiqidemall.dto.CarSellerGoods;
import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.entity.ShoppingCar;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.mapper.FirstTagMapper;
import com.feng.shuaiqidemall.mapper.ShoppingCarMapper;
import com.feng.shuaiqidemall.mapper.GoodMapper;
import com.feng.shuaiqidemall.service.BuyerService;
import com.feng.shuaiqidemall.service.CurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private FirstTagMapper firstTagMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private CurrentService currentService;

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Override
    public ResultDTO getTag() {
        return ResultDTO.success("找到标签",firstTagMapper.findTag());
    }

    @Override
    public ResultDTO searchGood(String tag) {
        String[] tags = tag.split("，");
        if (tags.length == 0) return ResultDTO.success("查找成功",goodMapper.findAll());
        List<Good> goods = goodMapper.selectByTag(tags[0]);
        for (String s : tags) {
            List<Good> list = goodMapper.selectByTag(s);
            goods.retainAll(list);
        }
        return ResultDTO.success("查找成功",goods);
    }

    //加入购物车
    @Override
    public ResultDTO addToCar(int goodId,int num) {
        Integer goodNum = goodMapper.selectByPrimaryKey(goodId).getNumber();
        if (num > goodNum){
            return ResultDTO.failure("加入失败，购物车中的数量大于库存数量");
        }
        UserInfo currentUser = currentService.getCurrentUser();
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setBuyerId(currentUser.getId());
        shoppingCar.setGoodId(goodId);
        shoppingCar.setNum(num);
        List<ShoppingCar> shoppingCars = shoppingCarMapper.selectByBuyerAndGood(shoppingCar);
        if (shoppingCars.size() != 0){
            //判断购物车数量是否大于库存
            if (shoppingCars.get(0).getNum()+num > goodNum){
                return ResultDTO.failure("加入失败，购物车中的数量大于库存数量");
            }
            shoppingCars.get(0).setNum(shoppingCars.get(0).getNum()+num);
            shoppingCarMapper.updateByPrimaryKey(shoppingCars.get(0));
            return ResultDTO.success("加入成功");
        }
        shoppingCarMapper.insert(shoppingCar);
        return ResultDTO.success("加入成功",shoppingCar);
    }

    //取出购物车中的商品
    @Override
    public ResultDTO getGoodsFromShoppingCar() {
        UserInfo currentUser = currentService.getCurrentUser();
        List<CarSellerGoods> goodsInCar = shoppingCarMapper.selectForSellerNameNumGood(currentUser.getId());
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
        return ResultDTO.success("搜索成功",sortByname);

    }

    //分析并创建订单
    @Override
    public ResultDTO createOrder(String goodId, String num) {
        if(goodId.isBlank() || num.isBlank()) return ResultDTO.failure("返回集为空");
        String[] goodIds = goodId.split("/");
        String[] nums = num.split("/");
        if (goodIds.length != nums.length) return ResultDTO.failure("返回集的物品和物品数量长度不一样");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime = simpleDateFormat.format(date);
        Integer s = Integer.valueOf(currentTime);
        for (int i = 0; i < goodIds.length; i++) {
            int id = Integer.valueOf(goodIds[0]);
            Good good = goodMapper.selectByPrimaryKey(id);
        }
        return null;
    }

}
