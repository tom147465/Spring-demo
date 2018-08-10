package com.zz.bill.controller;

import com.zz.bill.CommonCode;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.spend.SpendInputModel;
import com.zz.bill.service.spendBiz.ISpendBizService;
import com.zz.bill.util.CurrentUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/internal/spend")
public class SpendController {
    @Autowired
    private ISpendBizService spendBizService;

    @PostMapping(value = "/create")
    JsonResult createSpend(@RequestBody @Valid SpendInputModel spendInputModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new JsonResult(CommonCode.FAIL, msg, false);
        }

        return spendBizService.createSpend(spendInputModel);
    }

}
