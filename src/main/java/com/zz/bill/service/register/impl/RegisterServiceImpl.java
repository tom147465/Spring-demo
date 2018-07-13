package com.zz.bill.service.register.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.service.user.IUserService;
import com.zz.bill.entity.User;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.account.UserInfo;
import com.zz.bill.service.register.IRegisterService;
import com.zz.bill.util.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements IRegisterService{

    @Autowired
    private IUserService userService;

    @Override
    public JsonResult checkExist(String accountName) {
        if(!userService.checkExist(accountName))
            return JsonResult.builder().code(CommonCode.ACCOUNT_ALREADY_EXIST).msg("failure").data(false).build();
        return JsonResult.builder().code(CommonCode.SUCC).msg("succ").data(true).build();
    }

    @Override
    public JsonResult register(User user){
        try{
            userService.insertNewUser(user);
        } catch (RuntimeException e){
            return JsonResult.builder().code(CommonCode.SYS_ERR).msg(e.getMessage()).data(false).build();
        }
        String token = TokenHolder.getToken(user.getAccount());
        TokenHolder.setTokenForUserId(token, user.getId());

        UserInfo userInfo = UserInfo.builder()
                .account(user.getAccount())
                .uid(user.getId())
                .authToken(token)
                .build();

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ").data(userInfo).build();
    }
}
