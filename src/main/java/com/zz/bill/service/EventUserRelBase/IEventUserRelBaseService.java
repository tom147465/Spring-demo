package com.zz.bill.service.EventUserRelBase;

import com.zz.bill.entity.EventUserRel;

import java.util.List;

public interface IEventUserRelBaseService {
    List<Integer> insertNewEventUserRel(EventUserRel eventUserRel);
    List<EventUserRel> findAllByEventId(Integer eventId);
    List<Integer> deleteEventUserRel(EventUserRel eventUserRel);
    EventUserRel findByEventIdAndUid(Integer eventId, Integer uid);
    List<Integer> findAllUidsInEventByEventId(Integer eventId);
    List<Integer> insertMultiEventUserRel(List<EventUserRel> eventUserRelList);
}

