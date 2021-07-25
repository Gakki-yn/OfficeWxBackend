package com.example.emos.wx.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/*
   OAuth2Realm类是AuthorizingRealm 的实现类,在这个实现类中定义认证和授权的
方法。因为认证与授权模块设计到用户模块和权限模块，还没有真正的开发业务模块,所以
这里先暂时定义空的认证去授权方法
*/
public class OAuth2Realm extends AuthorizingRealm {

    private JwtUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof  OAuth2Token;
    }

    /*
        授权
         */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO 查询用户权限列表
        //TODO 吧权限列表添加到info对象中
        return info;
    }
     /*
      认证
       */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
      //TODO 从令牌中获取userId，然后检查该账户是否被冻结
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
      //TODO 往info对象中添加用户信息、token字符串
        return null;
    }
}