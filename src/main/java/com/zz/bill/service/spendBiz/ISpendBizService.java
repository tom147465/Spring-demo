package com.zz.bill.service.spendBiz;

import com.zz.bill.model.JsonResult;
import com.zz.bill.model.spend.SpendInputModel;

public interface ISpendBizService {
    JsonResult createSpend(SpendInputModel spendInputModel);
}
