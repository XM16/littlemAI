package com.hust.xiaomo16.repository;


import com.hust.xiaomo16.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserInfo,String > {
    @Query(value = "select * from user_info where user_info.username=?1", nativeQuery = true)
    UserInfo findUserByUserame(String username);
}
