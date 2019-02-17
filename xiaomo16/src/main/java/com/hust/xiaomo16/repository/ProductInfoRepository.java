package com.hust.xiaomo16.repository;


import com.hust.xiaomo16.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
//    List<ProductInfo> findByProductStatus(Integer productStatus);

}
