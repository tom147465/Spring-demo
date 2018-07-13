package com.zz.bill.service.register;

import com.zz.bill.entity.User;
import com.zz.bill.model.JsonResult;

public interface IRegisterService {
    JsonResult checkExist(String accountName);
    JsonResult register(User user);
}
