package com.hust.xiaomo16.service;

import com.hust.xiaomo16.entity.User;

import java.util.List;

public interface UserService {
    /**新用户注册*/
    void register(User user);

    /**注销用户*/
    void delete(String username);

    /**查询用户列表*/
    List<User> findUserList();

    /**更改用户密码*/
    void update(User user);

    /**查询单个用户*/
    User findUser(String username);
}
