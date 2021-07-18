package com.feng.shuaiqidemall;

import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.config.AccountSecurityCookieConfig;
import com.feng.shuaiqidemall.entity.FirstTag;
import com.feng.shuaiqidemall.mapper.FirstTagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShuaiqidemallApplicationTests {

    @Autowired
    AccountSecurityCookieConfig accountSecurityConfig;

    @Autowired
    FirstTagMapper firstTagMapper;

    @Test
    void contextLoads() {

    }

}
