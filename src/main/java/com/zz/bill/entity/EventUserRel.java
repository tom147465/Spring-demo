package com.zz.bill.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "event_user_rel")
public class EventUserRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer uid;

    @Column(name = "event_id")
    private Integer eventId;

    public EventUserRel(Integer uid, Integer eventId){
        this.uid = uid;
        this.eventId = eventId;
    }
}
