package com.zz.bill.service.eventBase;

import com.zz.bill.entity.Event;
import com.zz.bill.model.SpendShareInfo;
import com.zz.bill.model.event.EventStatus;

import java.util.List;

public interface IEventBaseService {
    Event insertNewEvent(Event event);
    EventStatus getStatusById(Integer eventId);
    Event findEventById(Integer eventId);
    List<SpendShareInfo> getSpendShareInfoInEvent(Integer eventId, Integer userId);
}
