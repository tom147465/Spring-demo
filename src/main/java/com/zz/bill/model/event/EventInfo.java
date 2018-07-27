package com.zz.bill.model.event;

import lombok.Builder;
import lombok.Data;
import com.zz.bill.entity.Event;
import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
public class EventInfo {


    private Integer id;

    private String eventName;

    private EventStatus status;

    private Integer creatorUid;

    private List<Integer> userIds;

    private Timestamp createdAt;


    public EventInfo(Event event){
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.status = event.getStatus();
        this.creatorUid = event.getCreatorUid();
        this.createdAt = event.getCreatedAt();
    }
}
