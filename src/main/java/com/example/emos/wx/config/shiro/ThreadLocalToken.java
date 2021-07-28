package com.example.emos.wx.config.shiro;

import org.springframework.stereotype.Component;

/*
hreadlocal而是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据，
 */
@Component
public class ThreadLocalToken {
    private ThreadLocal<String> local=new ThreadLocal<>();

    public void setToken(String token){
        local.set(token);
    }

    public String getToken(){
        return local.get();
    }

    public void clear(){
        local.remove();
    }
}
