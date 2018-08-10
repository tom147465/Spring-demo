package com.zz.bill.model;

import com.zz.bill.CommonCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JsonResult {

    private String code;
    private String msg;
    private Object data;
}
