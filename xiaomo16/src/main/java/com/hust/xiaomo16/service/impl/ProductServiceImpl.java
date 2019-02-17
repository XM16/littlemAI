package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.dto.CartDTO;
import com.hust.xiaomo16.entity.ProductInfo;
import com.hust.xiaomo16.enums.ProductStatusEnum;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.repository.ProductInfoRepository;
import com.hust.xiaomo16.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    @Cacheable(key = "1234")
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    @CachePut(key = "1234")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
    /**
     * 查询所有在架商品列表
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findAll();
    }

    @Override

    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new MyException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }

    }

//    @Override
//    public ProductInfo onSale(String productId) {
//        ProductInfo productInfo=repository.findOne(productId);
//        if(productInfo==null){
//            throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if(productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
//            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//        //更新
//        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
//
//        return repository.save(productInfo);
//    }
//
//    @Override
//    public ProductInfo offSale(String productId) {
//        ProductInfo productInfo=repository.findOne(productId);
//        if(productInfo==null){
//            throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if(productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
//            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//        //更新
//        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
//
//        return repository.save(productInfo);
//    }
}