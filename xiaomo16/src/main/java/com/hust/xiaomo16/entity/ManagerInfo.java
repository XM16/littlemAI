package com.hust.xiaomo16.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: xiaomo16
 * @description: 管理员信息
 * @author: Boon Guan
 * @create: 2019-01-14 14:37
 **/
@Entity
@Data
public class ManagerInfo {

    //管理员ID
    @Id
    private String managerId;

    //管理员用户名
    private String mUsername;
    //管理员密码
    private  String mPassword;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

}
