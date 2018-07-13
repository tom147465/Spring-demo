package com.zz.bill.config.interceptor;

import com.zz.bill.service.Redis.IRedisService;
import com.zz.bill.util.CurrentUserHolder;
import com.zz.bill.util.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private  static final String AUTH_HEADER = "X-Auth";

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);
        //TODO token 验证
        if (StringUtils.isEmpty(token) || !checkTokenValid(token)) {
            return false;
        }

        CurrentUserHolder.setCurrentUser(redisService.findUserByToken(token));
        // todo catch exception from findUserByToken

        //TODO token 验证失败 throw Auth failed Exception
        return false;
    }


    private Boolean checkTokenValid(String token){
        //todo function check Token  --> with machine info and login status?

        return true;
    }
}
