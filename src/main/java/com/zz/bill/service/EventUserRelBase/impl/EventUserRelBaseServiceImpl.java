package com.zz.bill.service.EventUserRelBase.impl;

import com.zz.bill.entity.EventUserRel;
import com.zz.bill.repo.EventUserRelRepo;
import com.zz.bill.service.EventUserRelBase.IEventUserRelBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUserRelBaseServiceImpl implements IEventUserRelBaseService {

    @Autowired
    private EventUserRelRepo eventUserRelRepo;

    @Override
    public List<EventUserRel> findAllByEventId(Integer eventId) {
        return eventUserRelRepo.findAllByEventId(eventId);
    }

    @Override
    public List<EventUserRel> deleteEventUserRel(EventUserRel eventUserRel) {
        eventUserRelRepo.delete(eventUserRel.getId());
        return eventUserRelRepo.findAllByEventId(eventUserRel.getEventId());
    }

    @Override
    public EventUserRel findByEventIdAndUid(Integer eventId, Integer uid) {
        return eventUserRelRepo.findByEventIdAndUid(eventId, uid);
    }

    @Override
    public List<EventUserRel> insertNewEventUserRel(EventUserRel eventUserRel){
        eventUserRelRepo.saveAndFlush(eventUserRelRepo);
        return eventUserRelRepo.findAllByEventId(eventUserRel.getEventId());
    }

}
