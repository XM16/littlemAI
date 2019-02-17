package com.hust.xiaomo16.form;

import lombok.Data;

/**
 * @program: XM16
 * @description:
 * @author: Boon Guan
 * @create: 2019-02-16 17:33
 **/

@Data
public class CategoryForm {

    private Integer categoryId;
    /**类目名字**/
    private String categoryName;
    /** 类目编号**/
    private Integer categoryType;

}
