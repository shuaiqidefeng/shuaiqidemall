package com.feng.shuaiqidemall.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.feng.shuaiqidemall.security.cookie")
public class AccountSecurityCookieConfig {

    private Integer maxAge;

    private Boolean httpOnly;

    private String path;

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Boolean getHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "AccountSecurityCookieConfig{" +
                "maxAge=" + maxAge +
                ", httpOnly=" + httpOnly +
                ", path='" + path + '\'' +
                '}';
    }
}
