package com.feng.shuaiqidemall.controller;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/tag")
    public ResultDTO getTag(){
        return buyerService.getTag();
    }

    @PostMapping("/search")
    public ResultDTO searchGood(@RequestBody String tag){
        return buyerService.searchGood(tag);
    }

    @GetMapping("/addToCar")
    public ResultDTO addToCar(int goodId,int goodNum){
        return buyerService.addToCar(goodId,goodNum);
    }

    @GetMapping("/getGoodsFromCar")
    public ResultDTO getGoodsFromCar(){
        return buyerService.getGoodsFromShoppingCar();
    }

    @GetMapping("/createOrder")
    public ResultDTO createOrder(String goodId,String num) {
        return null;
    }
}
