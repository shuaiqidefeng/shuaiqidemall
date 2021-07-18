package com.feng.shuaiqidemall.controller;


import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.UserInfo;
import com.feng.shuaiqidemall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResultDTO<UserInfo> userCreat(@RequestBody UserInfo user){
        return userService.create(user);
    }

    @PostMapping("/login")
    public ResultDTO<UserInfo> userLogin(@RequestBody UserInfo user){
        return userService.login(user);
    }

    @PostMapping("/update")
    public ResultDTO<UserInfo> userUpdate(@RequestBody UserInfo user){
        return userService.update(user);
    }

    @GetMapping("/logout")
    public ResultDTO<UserInfo> userLogout(){
        return userService.logout();
    }
}
