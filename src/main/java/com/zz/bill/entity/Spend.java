package com.zz.bill.entity;

import com.zz.bill.model.event.SpendStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "spend")
public class Spend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "describe")
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "who_prepay")
    private Integer whoPrepay;

    @Column(name = "status")
    private SpendStatus status;

    public Spend(Integer eventId, String name, BigDecimal amount, Integer whoPrepay){
        this.eventId = eventId;
        this.name = name;
        this.amount = amount;
        this.whoPrepay = whoPrepay;
        this.status = SpendStatus.ACTIVE;
    }
}
