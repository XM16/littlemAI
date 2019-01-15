package com.hust.xiaomo16.form;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @program: xiaomo16
 * @description: .
 * @author: Boon Guan
 * @create: 2019-01-14 15:53
 **/

@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;


    /**
     * 买家userId
     */
    @NotEmpty(message = "userId必填")
    private String userId;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
