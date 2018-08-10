package com.zz.bill.repo;

import com.zz.bill.entity.Spend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendRepo extends JpaRepository<Spend, Integer> {
}
