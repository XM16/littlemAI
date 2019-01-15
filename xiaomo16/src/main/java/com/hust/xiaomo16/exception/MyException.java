package com.hust.xiaomo16.exception;

import com.hust.xiaomo16.enums.ResultEnum;

/**
 * @program: xiaomo16
 * @description: 自定义异常类
 * @author: Boon Guan
 * @create: 2019-01-14 15:43
 **/

public class MyException extends RuntimeException{

    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
