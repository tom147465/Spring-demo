package com.zz.bill.service.login;

import com.zz.bill.model.JsonResult;

public interface ILoginService {
    JsonResult login(String account, String pwd);
}
