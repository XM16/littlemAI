package com.hust.xiaomo16.repository;

import com.hust.xiaomo16.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: xiaomo16
 * @description:DAO
 * @author: Boon Guan
 * @create: 2019-01-14 14:55
 **/

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerUserId(String buyerOpenid, Pageable pageable);
}
