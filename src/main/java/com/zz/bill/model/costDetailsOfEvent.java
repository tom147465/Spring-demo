package com.zz.bill.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class costDetailsOfEvent {
    BigDecimal totlePrepay;
    BigDecimal totleShare;
    List<SpendShareInfo> spendShareInfos;
}
