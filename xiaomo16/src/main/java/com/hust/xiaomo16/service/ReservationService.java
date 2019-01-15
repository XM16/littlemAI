package com.hust.xiaomo16.service;

import com.hust.xiaomo16.entity.ReservationDetail;

import java.util.List;

public interface ReservationService {
    /**新增预约*/
    void create(ReservationDetail reservationDetail);

    /**取消预约或删除预约记录*/
    void cancel(String userID);

    /**查询预约列表*/
    List<ReservationDetail> findList();

    /**查询目前排队情况*/
    Integer findOne(Integer tableType);

    /**更改排队状态*/
    void updateStatus(ReservationDetail rd);

}
