package com.feng.shuaiqidemall.controller;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //上架商品
    @PostMapping("/shelve")
    public ResultDTO shelve(@RequestBody Good good){
        return sellerService.insertGood(good);
    }

}
