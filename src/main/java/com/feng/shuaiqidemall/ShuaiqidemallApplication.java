package com.feng.shuaiqidemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.feng.shuaiqidemall.mapper")
public class ShuaiqidemallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShuaiqidemallApplication.class, args);
    }



}
