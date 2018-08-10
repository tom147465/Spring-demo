package com.zz.bill.model.spend;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SpendInputModel {
    @NotNull(message = "eventId cannot be null!")
    Integer eventId;

    @NotNull(message = "spendName cannot be null!")
    String spendName;

    @NotNull(message = "amount cannot be null!")
    BigDecimal amount;

    @NotNull(message = "prepayUid cannot be null!")
    Integer prepayUid;

    @NotNull(message = "shareUids cannot be null!")
    List<Integer> shareUids;
}
