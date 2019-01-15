package com.hust.xiaomo16.enums;

import lombok.Getter;

/**
 * @program: xiaomo16
 * @description: 支付状态枚举
 * @author: Boon Guan
 * @create: 2019-01-14 14:50
 **/

@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
