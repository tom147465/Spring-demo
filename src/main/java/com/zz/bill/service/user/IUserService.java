package com.zz.bill.service.user;

import com.zz.bill.entity.User;
import com.zz.bill.model.account.UserInfo;

public interface IUserService {
    void insertNewUser(User user);
    User getUserByAccount(String account);
    User getUserById(Integer userId);
    Boolean checkExist(String accountName);
    void updateUserInfo(UserInfo userinfo);
}
