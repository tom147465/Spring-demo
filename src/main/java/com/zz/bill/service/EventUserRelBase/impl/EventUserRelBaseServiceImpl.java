package com.zz.bill.service.EventUserRelBase.impl;

import com.zz.bill.entity.EventUserRel;
import com.zz.bill.repo.EventUserRelRepo;
import com.zz.bill.service.EventUserRelBase.IEventUserRelBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service
public class EventUserRelBaseServiceImpl implements IEventUserRelBaseService{
    private EntityManager em;
    @PersistenceContext(name = "EntityManagerFactory")
    public void SetEntityManager(EntityManager em) {
        this.em = em;
    }


    @Autowired
    private EventUserRelRepo eventUserRelRepo;

    @Override
    public List<EventUserRel> findAllByEventId(Integer eventId) {
        return eventUserRelRepo.findAllByEventId(eventId);
    }

    @Override
    public List<Integer> deleteEventUserRel(EventUserRel eventUserRel) {
        eventUserRelRepo.delete(eventUserRel.getId());
        return eventUserRelRepo.findAllUidByEventId(eventUserRel.getEventId());
    }

    @Override
    public EventUserRel findByEventIdAndUid(Integer eventId, Integer uid) {
        return eventUserRelRepo.findByEventIdAndUid(eventId, uid);
    }

    @Override
    public List<Integer> findAllUidsInEventByEventId(Integer eventId) {
        return eventUserRelRepo.findAllUidByEventId(eventId);
    }

    @Override
    public List<Integer> insertMultiEventUserRel(List<EventUserRel> eventUserRelList) {
        Integer eventId = eventUserRelList.get(0).getEventId();
        eventUserRelRepo.save(eventUserRelList);
        eventUserRelRepo.flush();
        return eventUserRelRepo.findAllUidByEventId(eventId);
    }

    @Override
    public List<Integer> insertNewEventUserRel(EventUserRel eventUserRel){
        eventUserRelRepo.saveAndFlush(eventUserRel);
        return eventUserRelRepo.findAllUidByEventId(eventUserRel.getEventId());
    }

}
