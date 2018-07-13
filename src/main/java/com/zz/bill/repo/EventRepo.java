package com.zz.bill.repo;

import com.zz.bill.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Integer> {
}
