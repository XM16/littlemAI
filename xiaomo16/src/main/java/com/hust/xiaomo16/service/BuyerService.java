package com.hust.xiaomo16.service;

import com.hust.xiaomo16.dto.OrderDTO;

/**
 * @program: xiaomo16
 * @description:
 * @author: Boon Guan
 * @create: 2019-01-14 15:23
 **/

public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
