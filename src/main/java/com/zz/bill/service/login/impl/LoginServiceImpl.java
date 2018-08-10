package com.zz.bill.service.login.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.service.user.IUserService;
import com.zz.bill.entity.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.service.login.ILoginService;
import com.zz.bill.util.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserService userService;

    @Override
    public JsonResult login(String account, String pwd){
        User user = userService.getUserByAccount(account);
        if (user == null)
            return JsonResult.builder().code(CommonCode.ACCOUNT_NOT_EXIST).msg("account not exist")
                    .data(null).build();

        if(!pwd.equals(user.getPwd()))
            return JsonResult.builder().code(CommonCode.WRONG_PWD).msg("wrong pwd")
                    .data(null).build();

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(TokenHolder.getToken(account)).build();
    }
}
