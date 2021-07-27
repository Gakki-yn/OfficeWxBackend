package com.example.emos.wx.config.shiro;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
  定义OAuth2Filter类拦截所有的HTTP请求，一方面它会把请求中的Token 字符串提取出
来,封装成对象交给Shiro框架;另一方面,它会检查Token 的有效性。如果Token 过期,那么
会生成新的Token；分别存储在ThreadLocalToken和Redis中。
  因为在0Auth2Filter 类中要读写ThreadLocal 中的数据，所以0Auth2Filter 类
必须要设置成多例的，否则ThreadLocal 将无法使用。

 */
@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {

    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req=(HttpServletRequest) request;
         String token=getRequestToken(req);
         if(StrUtil.isBlank(token)){
             return null;
         }
        return new OAuth2Token(token);
    }
//判断哪些哪些请求交由shrio处理
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req=(HttpServletRequest) request;
        if(req.getMethod().equals(RequestMethod.OPTIONS.name())){
              return true;//放行
        }
           return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }

    private String getRequestToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token=request.getParameter("token");
        }
        return token;
    }
}