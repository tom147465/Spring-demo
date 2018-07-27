package com.zz.bill.repo;

import com.zz.bill.entity.EventUserRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventUserRelRepo extends JpaRepository<EventUserRel, Integer> {
    List<EventUserRel> findAllByEventId(Integer eventId);
    EventUserRel findByEventIdAndUid(Integer eventId, Integer uid);
    List<EventUserRel> deleteEventUserRelRepoByEventIdAndUid(Integer eventId, Integer uid);

    @Query(value = "select user_id from event_user_rel where event_id = ?1", nativeQuery = true)
    List<Integer> findAllUidByEventId(Integer eventId);
}
