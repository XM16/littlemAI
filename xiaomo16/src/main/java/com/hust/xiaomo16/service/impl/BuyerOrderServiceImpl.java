package com.hust.xiaomo16.service.impl;
import com.hust.xiaomo16.entity.OrderDetail;
import com.hust.xiaomo16.entity.OrderMaster;
import com.hust.xiaomo16.repository.OrderDetailRepository;
import com.hust.xiaomo16.repository.OrderMasterRepository;
import com.hust.xiaomo16.repository.ProductInfoRepository;
import com.hust.xiaomo16.service.BuyerOrderService;
import com.hust.xiaomo16.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class BuyerOrderServiceImpl implements BuyerOrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void addOrderMater(OrderMaster orderMaster){
        orderMasterRepository.save(orderMaster);
    }

}
