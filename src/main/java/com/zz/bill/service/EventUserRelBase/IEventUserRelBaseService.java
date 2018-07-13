package com.zz.bill.service.EventUserRelBase;

import com.zz.bill.entity.EventUserRel;

import java.util.List;

public interface IEventUserRelBaseService {
    List<EventUserRel> insertNewEventUserRel(EventUserRel eventUserRel);
    List<EventUserRel> findAllByEventId(Integer eventId);
    List<EventUserRel> deleteEventUserRel(EventUserRel eventUserRel);
    EventUserRel findByEventIdAndUid(Integer eventId, Integer uid);
}

