package com.zz.bill.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendShareInfo {
    String spendName;
    BigDecimal shareAmount;
    BigDecimal prepayAmount;
}
