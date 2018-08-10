package com.zz.bill.service.spendBase.impl;

import com.zz.bill.entity.Share;
import com.zz.bill.entity.Spend;
import com.zz.bill.repo.ShareRepo;
import com.zz.bill.repo.SpendRepo;
import com.zz.bill.service.spendBase.ISpendBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendBaseServiceImpl implements ISpendBaseService {

    @Autowired
    private SpendRepo spendRepo;
    @Autowired
    private ShareRepo shareRepo;

    @Override
    public Boolean insertNewSpend(Spend newSpend, BigDecimal shareAmount, List<Integer> shareUids) {
        Spend spend = spendRepo.save(newSpend);
        if(spend == null) return false;

        BigDecimal receiveAmount = new BigDecimal("0").subtract(shareAmount);
        List<Share> newShares = shareUids.parallelStream()
                .map(uid -> new Share(shareAmount, (uid.equals(spend.getWhoPrepay())?spend.getAmount().add(receiveAmount):receiveAmount), uid, spend.getId()))
                .collect(Collectors.toList());
        newShares = shareRepo.save(newShares);
        if(newShares == null) return false;

        spendRepo.flush();
        shareRepo.flush();

        return true;
    }

    public Spend findSpendBySpendId(Integer spendId){
        return spendRepo.findOne(spendId);
    }
}
