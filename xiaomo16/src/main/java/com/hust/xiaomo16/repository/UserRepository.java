package com.hust.xiaomo16.repository;

import com.hust.xiaomo16.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
