package com.zz.bill.model.event;

import com.zz.bill.entity.EventUserRel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventPayloadData {
    private EventInfo eventInfo;
    private List<EventUserRel> listUserInEvent; // eventId 冗余
    // List<Integer> userUids ;
}
