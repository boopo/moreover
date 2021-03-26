package com.lv.spring.context;

import io.swagger.models.auth.In;

import java.util.Map;

public class UserContext {
    private static final ThreadLocal<String> user = new ThreadLocal<String>();

    public static void add(String userName) {
        user.set(userName);
    }

    public static void addId(String id){

    }

    public static void remove() {
        user.remove();
    }

    /**
     * @return 当前登录用户的用户名
     */
    public static String getCurrentUserName() {
        return user.get();
    }
}
