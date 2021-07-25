package com.example.emos.wx.config.shiro;


import org.apache.shiro.authc.AuthenticationToken;


/*
  把令牌封装成认证对象
 Shiro框架的认证需要用到认证对象
* */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public  OAuth2Token(String token){
        this.token=token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}