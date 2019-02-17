package com.hust.xiaomo16.service;

import com.hust.xiaomo16.entity.ManagerInfo;
import com.hust.xiaomo16.entity.User;

import java.util.List;

public interface ManagerService {
    /**新管理员注册*/
    ManagerInfo register(ManagerInfo managerInfo);

    /**注销管理员账户*/
    void delete(String mUsername);

    /**查询管理员列表*/
    List<ManagerInfo> findManagerList();

    /**更改管理员密码*/
    void update(ManagerInfo managerInfo);

    /**查询单个管理员*/
    ManagerInfo findManager(String mUsername);
}
