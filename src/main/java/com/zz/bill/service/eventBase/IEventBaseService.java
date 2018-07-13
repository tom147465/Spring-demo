package com.zz.bill.service.eventBase;

import com.zz.bill.entity.Event;
import com.zz.bill.model.event.EventStatus;

public interface IEventBaseService {
    Event insertNewEvent(Event event);
    EventStatus getStatusById(Integer eventId);
    Event findEventById(Integer eventId);
}
