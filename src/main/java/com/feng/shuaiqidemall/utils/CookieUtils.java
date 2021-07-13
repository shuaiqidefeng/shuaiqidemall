package com.feng.shuaiqidemall.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static final int DELETE = 0;

    public static final int SESSION = -1;

    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

}
