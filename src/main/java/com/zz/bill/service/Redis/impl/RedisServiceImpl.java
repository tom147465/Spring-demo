package com.zz.bill.service.Redis.impl;

import com.zz.bill.entity.User;
import com.zz.bill.service.Redis.IRedisService;
import com.zz.bill.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    private IUserService userService;

    @Override
    public Boolean checkTokenExist(String token){
        return stringRedisTemplate.hasKey(token);
    }

    @Override
    public User findUserByToken(String token){
        if(!checkTokenExist(token)){
            //todo throw excpetion.
        }
        Integer uid = Integer.parseInt(stringRedisTemplate.opsForValue().get(token));

        return userService.getUserById(uid);
    }

    @Override
    public String setTokenForUserId(Integer userId, String token){
        stringRedisTemplate.opsForValue().set(token, userId.toString(), 5L, TimeUnit.MINUTES);
        return token;
    }

}
