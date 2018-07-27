package com.zz.bill.entity;

import com.zz.bill.model.event.EventStatus;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String eventName;

    @Column(name = "status")
    private EventStatus status;

    @Column(name = "creator_uid")
    private Integer creatorUid;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    public void prePersist(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdAt = timestamp;
    }

    public Event(String eventName, Integer creatorUid){
        this.eventName = eventName;
        this.creatorUid = creatorUid;
    }


}

