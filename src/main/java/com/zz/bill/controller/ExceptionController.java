package com.zz.bill.controller;

import com.zz.bill.CommonCode;
import com.zz.bill.model.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        return JsonResult.builder().code(CommonCode.SYS_ERR).msg(e.getMessage())
                .data(false).build();
    }
}
