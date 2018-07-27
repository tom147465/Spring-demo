package com.zz.bill.service.eventBase.impl;

import com.zz.bill.entity.Event;
import com.zz.bill.model.JsonResult;
import com.zz.bill.model.event.EventInfo;
import com.zz.bill.model.event.EventStatus;
import com.zz.bill.repo.EventRepo;
import com.zz.bill.repo.UserRepo;
import com.zz.bill.service.eventBase.IEventBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBaseServiceImpl implements IEventBaseService {

    @Autowired
    private EventRepo eventRepo;

    @Override
    public Event insertNewEvent(Event event) {
        return eventRepo.saveAndFlush(event);
    }

    @Override
    public EventStatus getStatusById(Integer eventId) {
        return eventRepo.findOne(eventId).getStatus();
    }

    @Override
    public Event findEventById(Integer eventId) {
        return eventRepo.findOne(eventId);
    }
}
