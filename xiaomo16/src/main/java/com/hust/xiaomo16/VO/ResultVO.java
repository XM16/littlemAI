package com.hust.xiaomo16.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xiaomo16
 * @description: http请求返回的最外层对象
 * @author: Boon Guan
 * @create: 2019-01-14 15:36
 **/
//http请求返回的最外层对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
