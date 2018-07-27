package com.zz.bill.service.eventBiz.impl;

import com.zz.bill.CommonCode;
import com.zz.bill.entity.Event;
import com.zz.bill.entity.EventUserRel;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;
import com.zz.bill.model.event.EventPayloadData;
import com.zz.bill.model.event.EventStatus;
import com.zz.bill.service.EventUserRelBase.IEventUserRelBaseService;
import com.zz.bill.service.eventBase.IEventBaseService;
import com.zz.bill.service.eventBiz.IEventBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventBizServiceImpl implements IEventBizService {

    @Autowired
    private IEventBaseService eventBaseService;
    @Autowired
    private IEventUserRelBaseService eventUserRelBaseService;


    @Override
    public JsonResult createEvent(EventInfo eventInfo, Integer creatorUid) {


        Event newEvent = eventBaseService.insertNewEvent(new Event(eventInfo.getEventName(), creatorUid));
        eventUserRelBaseService.insertNewEventUserRel(new EventUserRel(newEvent.getId(), creatorUid));
        List<Integer> userUids = eventUserRelBaseService.findAllUidsInEventByEventId(newEvent.getId());

        EventInfo returnEventInfo = new EventInfo(newEvent);
        returnEventInfo.setUserIds(userUids);

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(returnEventInfo).build();
    }

    @Override
    public JsonResult joinWithScan(Integer eventId, Integer joinUid) {
        if (!eventBaseService.getStatusById(eventId).equals(EventStatus.IN_PROGRESS))
            return JsonResult.builder().code(CommonCode.FAIL).msg("Event was ended!")
                    .data(null).build();

        EventInfo eventInfo = new EventInfo(eventBaseService.findEventById(eventId));
        eventInfo.setUserIds(eventUserRelBaseService.insertNewEventUserRel(new EventUserRel(eventId, joinUid)));

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventInfo).build();
    }

    @Override
    public JsonResult joinWithInvite(Integer eventId, List<Integer> userIds) {
        List<EventUserRel> eventUserRelList = userIds.parallelStream()
                .map(uid -> new EventUserRel(eventId, uid)).collect(Collectors.toList());

        List<Integer> listUserIds = eventUserRelBaseService.insertMultiEventUserRel(eventUserRelList);

        EventInfo eventInfo = new EventInfo(eventBaseService.findEventById(eventId));
        eventInfo.setUserIds(listUserIds);

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventInfo).build();
    }

    @Override
    public JsonResult removeUserFromEvent(Integer eventId, Integer removeUid) {
        EventUserRel eventUserRel = eventUserRelBaseService.findByEventIdAndUid(eventId, removeUid);
        List<Integer> listUserIds = eventUserRelBaseService.deleteEventUserRel(eventUserRel);

        EventInfo eventInfo = new EventInfo(eventBaseService.findEventById(eventId));
        eventInfo.setUserIds(listUserIds);

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventInfo).build();
    }
}
