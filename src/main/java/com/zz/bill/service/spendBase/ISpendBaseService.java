package com.zz.bill.service.spendBase;

import com.zz.bill.entity.Share;
import com.zz.bill.entity.Spend;

import java.math.BigDecimal;
import java.util.List;

public interface ISpendBaseService {
    Boolean insertNewSpend(Spend newSpend, BigDecimal shareAmount, List<Integer> shareUids);
}
