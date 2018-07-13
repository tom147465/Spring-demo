package com.zz.bill.repo;

import com.zz.bill.entity.EventUserRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventUserRelRepo extends JpaRepository<EventUserRelRepo, Integer> {
    List<EventUserRel> findAllByEventId(Integer eventId);
    EventUserRel findByEventIdAndUid(Integer eventId, Integer uid);
    List<EventUserRel> deleteEventUserRelRepoByEventIdAndUid(Integer eventId, Integer uid);
}
