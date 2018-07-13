package com.zz.bill.repo;

import com.zz.bill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByAccount(String account);
}
