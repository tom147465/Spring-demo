package com.zz.bill.entity;

import com.zz.bill.model.event.EventInfo;
import com.zz.bill.model.event.EventStatus;
import lombok.Data;

import javax.persistence.*;


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

    public Event(String eventName, Integer creatorUid){
        this.eventName = eventName;
        this.creatorUid = creatorUid;
    }

    public EventInfo toEventInfo(){
        return EventInfo.builder().id(this.id).eventName(this.eventName)
                .status(this.status).creatorUid(this.creatorUid)
                .build();
    }
}

