package com.zz.bill.service.spendBiz.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.EventUserRel;
import com.zz.bill.entity.Share;
import com.zz.bill.entity.Spend;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.spend.SpendInputModel;
import com.zz.bill.service.spendBase.ISpendBaseService;
import com.zz.bill.service.spendBiz.ISpendBizService;
import com.zz.bill.util.CurrentUserHolder;
import com.zz.bill.util.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SpendBizServiceImpl implements ISpendBizService {

    @Autowired
    private ISpendBaseService spendBaseService;

    @Override
    public JsonResult createSpend(SpendInputModel spendInputModel) {
        BigDecimal shareAmount = spendInputModel.getAmount()
                .divide(new BigDecimal(spendInputModel.getShareUids().size()), 2, RoundingMode.HALF_DOWN);

        Spend newSpend = new Spend(spendInputModel.getEventId(), spendInputModel.getSpendName(),
                spendInputModel.getAmount(), spendInputModel.getPrepayUid());

        if(!spendBaseService.insertNewSpend(newSpend, shareAmount, spendInputModel.getShareUids()))
            return new JsonResult(CommonCode.FAIL, "fail", false);

        return new JsonResult(CommonCode.SUCC, "succ", true);
    }
}
