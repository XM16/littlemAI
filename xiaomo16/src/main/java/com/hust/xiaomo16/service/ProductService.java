package com.hust.xiaomo16.service;


import com.hust.xiaomo16.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//商品
public interface ProductService {
    ProductInfo findOne(String productId);
    //查询所有在架商品列表
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    //加库存
    //减库存
}
