package com.feng.shuaiqidemall;

import com.feng.shuaiqidemall.config.AccountSecurityConfig;
import com.feng.shuaiqidemall.config.AccountSecurityCookieConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShuaiqidemallApplicationTests {

    @Autowired
    AccountSecurityCookieConfig accountSecurityConfig;

    @Test
    void contextLoads() {
        System.out.println(accountSecurityConfig);
    }

}
