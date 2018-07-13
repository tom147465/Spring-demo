package com.zz.bill.service.Redis;

import com.zz.bill.entity.User;

public interface IRedisService {
    Boolean checkTokenExist(String token);
    String setTokenForUserId(Integer userId, String token);
    User findUserByToken(String token);
}
