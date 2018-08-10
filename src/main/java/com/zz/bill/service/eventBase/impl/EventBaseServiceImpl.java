package com.zz.bill.service.eventBase.impl;

import com.zz.bill.entity.Event;
import com.zz.bill.model.SpendShareInfo;
import com.zz.bill.model.event.EventStatus;
import com.zz.bill.repo.EventRepo;
import com.zz.bill.service.eventBase.IEventBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class EventBaseServiceImpl implements IEventBaseService {

    @Autowired
    private EventRepo eventRepo;

    @PersistenceContext
    private EntityManager em;

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

//    @SuppressWarnings("unchecked")
    @Override
    public List<SpendShareInfo> getSpendShareInfoInEvent(Integer eventId, Integer userId){

        String sql = "SELECT t1.name, t1.share_amount, t2.prepay " +
                "FROM (SELECT spend.id AS spend_id, spend.describe AS name, share.amount AS share_amount FROM spend,share " +
                        "WHERE spend.event_id = "+eventId+" AND spend.status = 1 AND share.user_id = "+ userId +
                        "AND share.spend_id = spend.id) t1 " +
                "LEFT JOIN " +
                    "(SELECT id, amount AS prepay FROM spend " +
                    "WHERE spend.event_id = "+ eventId+ " AND spend.status = 1 AND who_prepay = "+ userId +") t2 " +
                "ON(t1.spend_id = t2.id)";

        return em.createNativeQuery(sql, SpendShareInfo.class).getResultList();
    }
}
