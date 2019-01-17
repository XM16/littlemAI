package com.hust.xiaomo16.utils;

import java.util.Random;

/**
 * @program: xiaomo16
 * @description: 生成唯一主键
 * @author: Boon Guan
 * @create: 2019-01-14 15:40
 **/

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
