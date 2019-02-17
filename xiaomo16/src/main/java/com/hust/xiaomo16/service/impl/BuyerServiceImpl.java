package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.dto.OrderDTO;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
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
    public OrderDTO findOrderOne(String userId, String orderId) {
        return checkOrderOwner(userId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String userId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(userId, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new MyException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String userId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerUserid().equalsIgnoreCase(userId)) {
            log.error("【查询订单】订单的userId不一致. userId={}, orderDTO={}", userId, orderDTO);
            throw new MyException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
