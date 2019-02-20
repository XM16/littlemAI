package com.hust.xiaomo16.service;

import com.hust.xiaomo16.entity.OrderDetail;
import com.hust.xiaomo16.entity.OrderMaster;

public interface BuyerOrderService {

    void addOrderMater(OrderMaster orderMaster);

    void addOrderDetail(OrderDetail orderDetail);
}
