package com.hust.xiaomo16.enums;

import lombok.Getter;

/**
 * @program: xiaomo16
 * @description: 订单状态枚举
 * @author: Boon Guan
 * @create: 2019-01-14 14:47
 **/

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}