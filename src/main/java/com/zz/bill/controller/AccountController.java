package com.zz.bill.controller;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.User;
import com.zz.bill.model.account.AccountPwdPair;
import com.zz.bill.model.JsonResult;
import com.zz.bill.service.login.ILoginService;
import com.zz.bill.service.register.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/account/{version}")
public class AccountController {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IRegisterService registerService;

    @RequestMapping(value = "/register/check/{accountName}", method = RequestMethod.GET)
    public JsonResult checkAccountExist(@PathVariable String accountName) {
        if(accountName == null)
            return JsonResult.builder().code(CommonCode.PARAM_ERR).msg("null parameter").data(false).build();
        return registerService.checkExist(accountName);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return JsonResult.builder().code(CommonCode.PARAM_ERR).msg(msg).data(false).build();
        }
        return registerService.register(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody @Valid AccountPwdPair accountPwdPair, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return JsonResult.builder().code(CommonCode.PARAM_ERR).msg(msg)
                    .data(false).build();
        }
        return loginService.login(accountPwdPair.getAccount(), accountPwdPair.getPwd());
    }
}
