package com.hust.xiaomo16.repository;


import com.hust.xiaomo16.entity.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationDetail,
        Integer> {
}
