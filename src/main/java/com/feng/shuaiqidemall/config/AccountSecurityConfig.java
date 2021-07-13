package com.feng.shuaiqidemall.config;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "com.feng.shuaiqidemall.security")
public class AccountSecurityConfig {

    private String adminRoleName;

    private String tokenName;

    private Long expTime;

    private String[] allowUri;

    private String rootMapping;

    private Boolean usingDto;

    private AccountSecurityCookieConfig cookie;

    public String getAdminRoleName() {
        return adminRoleName;
    }

    public void setAdminRoleName(String adminRoleName) {
        this.adminRoleName = adminRoleName;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }

    public String[] getAllowUri() {
        return allowUri;
    }

    public void setAllowUri(String[] allowUri) {
        this.allowUri = allowUri;
    }

    public String getRootMapping() {
        return rootMapping;
    }

    public void setRootMapping(String rootMapping) {
        this.rootMapping = rootMapping;
    }

    public Boolean getUsingDto() {
        return usingDto;
    }

    public void setUsingDto(Boolean usingDto) {
        this.usingDto = usingDto;
    }

    public AccountSecurityCookieConfig getCookie() {
        return cookie;
    }

    public void setCookie(AccountSecurityCookieConfig cookie) {
        this.cookie = cookie;
    }


    @Override
    public String toString() {
        return "AccountSecurityConfig{" +
                "adminRoleName='" + adminRoleName + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", expTime=" + expTime +
                ", allowUri=" + Arrays.toString(allowUri) +
                ", rootMapping='" + rootMapping + '\'' +
                ", usingDto=" + usingDto +
                ", cookie=" + cookie +
                '}';
    }
}
