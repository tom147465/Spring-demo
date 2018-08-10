package com.zz.bill.entity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "share")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    //todo
    @Column
    private BigDecimal receiveAmount;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "spend_id")
    private Integer spendId;

    public Share(BigDecimal amount,BigDecimal receiveAmount, Integer userId, Integer spendId){
        this.amount = amount;
        this.receiveAmount = receiveAmount;
        this.userId = userId;
        this.spendId = spendId;
    }
}
