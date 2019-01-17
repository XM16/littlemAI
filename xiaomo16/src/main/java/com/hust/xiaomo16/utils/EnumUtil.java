package com.hust.xiaomo16.utils;

import com.hust.xiaomo16.enums.CodeEnum;

/**
 * @program: xiaomo16
 * @description: .
 * @author: Boon Guan
 * @create: 2019-01-15 16:15
 **/

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
