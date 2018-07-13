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

@Service
public class EventBizServiceImpl implements IEventBizService {

    @Autowired
    private IEventBaseService eventBaseService;
    @Autowired
    private IEventUserRelBaseService eventUserRelBaseService;


    @Override
    public JsonResult createEvent(EventInfo eventInfo, Integer creatorUid) {


        Event newEvent = eventBaseService.insertNewEvent(new Event(eventInfo.getEventName(), creatorUid));

        List<EventUserRel> listUserInEvent = eventUserRelBaseService
                .insertNewEventUserRel(new EventUserRel(newEvent.getId(), creatorUid));

        EventPayloadData eventPayloadData = EventPayloadData.builder()
                .eventInfo(newEvent.toEventInfo())
                .listUserInEvent(listUserInEvent)
                .build();

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventPayloadData).build();
    }

    @Override
    public JsonResult joinWithScan(Integer eventId, Integer joinUid) {
        if (!eventBaseService.getStatusById(eventId).equals(EventStatus.IN_PROGRESS))
            return JsonResult.builder().code(CommonCode.FAIL).msg("Event was ended!")
                    .data(null).build();

        return joinEvent(eventId, joinUid);
    }
    @Override
    public JsonResult joinWithInvite(Integer eventId, Integer joinUid) {
        return joinEvent(eventId, joinUid);
    }

    @Override
    public JsonResult removeUserFromEvent(Integer eventId, Integer removeUid) {
        EventUserRel eventUserRel = eventUserRelBaseService.findByEventIdAndUid(eventId, removeUid);
        List<EventUserRel> listUserInEvent = eventUserRelBaseService.deleteEventUserRel(eventUserRel);

        // listUser --> users' id

        EventPayloadData eventPayloadData = EventPayloadData.builder()
                .eventInfo(eventBaseService.findEventById(eventId).toEventInfo())
                .listUserInEvent(listUserInEvent)
                .build();

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventPayloadData).build();
    }

    private JsonResult joinEvent(Integer eventId, Integer joinUid){

        eventUserRelBaseService.insertNewEventUserRel(new EventUserRel(eventId, joinUid));
        List<EventUserRel> listUserInEvent = eventUserRelBaseService.findAllByEventId(eventId);
        EventPayloadData eventPayloadData = EventPayloadData.builder()
                .eventInfo(eventBaseService.findEventById(eventId).toEventInfo())
                .listUserInEvent(listUserInEvent)
                .build();

        return JsonResult.builder().code(CommonCode.SUCC).msg("succ")
                .data(eventPayloadData).build();
    }
}
