package com.zz.bill.service.user.impl;

import com.zz.bill.repo.UserRepo;
import com.zz.bill.service.user.IUserService;
import com.zz.bill.entity.User;
import com.zz.bill.model.account.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{

    private Map<String, User> users;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void insertNewUser(User user) {
        userRepo.saveAndFlush(user);
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepo.findByAccount(account);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepo.findOne(userId);
    }

    @Override
    public Boolean checkExist(String accountName) {
        if(userRepo.findByAccount(accountName)==null)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public void updateUserInfo(UserInfo userinfo) {
        User user = userRepo.getOne(userinfo.getUid());
        user.setNickName(userinfo.getNickName());
        user.setAvatar(userinfo.getAvatar());

        userRepo.saveAndFlush(user);
    }
}
