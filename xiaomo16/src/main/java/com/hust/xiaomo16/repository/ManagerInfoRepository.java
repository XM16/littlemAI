package com.hust.xiaomo16.repository;

import com.hust.xiaomo16.entity.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: sell
 * @description:
 * @author: Boon Guan
 * @create: 2019-01-24 11:31
 **/

public interface ManagerInfoRepository extends JpaRepository<ManagerInfo,String> {
   // ManagerInfo findByManagerId(String managerId);
    @Query(value = "select * from manager_info where manager_info.m_username=?1", nativeQuery = true)

    ManagerInfo findManagerByMuserame(String mUsername);





}
