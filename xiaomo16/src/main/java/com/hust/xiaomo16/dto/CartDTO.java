package com.hust.xiaomo16.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @program: xiaomo16
 * @description: .购物车
 * @author: Boon Guan
 * @create: 2019-01-15 17:10
 **/

@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
