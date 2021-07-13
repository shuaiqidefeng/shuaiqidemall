package com.feng.shuaiqidemall.controller;


import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.Buyer;
import com.feng.shuaiqidemall.entity.Seller;
import com.feng.shuaiqidemall.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/buyer/create")
    public ResultDTO<Buyer> buyerCreat(@RequestBody Buyer buyer){
        return buyerService.create(buyer);
    }

    @PostMapping("/seller/create")
    public ResultDTO<Seller> sellerCreat(@RequestBody Seller seller){
        return buyerService.create(seller);
    }

    @PostMapping("/buyer/login")
    public ResultDTO<Buyer> buyerLogin(@RequestBody Buyer buyer){
        return buyerService.login(buyer);
    }

    @PostMapping("/seller/login")
    public ResultDTO<Buyer> sellerLogin(@RequestBody Seller seller){
        return buyerService.login(seller);
    }

}
