package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.dto.OrderDTO;
import com.hust.xiaomo16.service.BuyerService;
import com.hust.xiaomo16.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xiaomo16
 * @description:
 * @author: Boon Guan
 * @create: 2019-01-14 15:25
 **/

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        return null;
    }
}
