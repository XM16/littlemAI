package com.hust.xiaomo16.service.impl;


import com.hust.xiaomo16.entity.UserInfo;
import com.hust.xiaomo16.repository.UserRepository;
import com.hust.xiaomo16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserInfo register(UserInfo user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        userRepository.delete(username);
    }

//    @Override
//    public List<User> findUserList() {
//        return userRepository.findAll();
//    }

    @Override
    public void update(UserInfo user) {
             userRepository.save(user);
    }

    @Override
    public UserInfo findUser(String username) {
        return userRepository.findUserByUserame(username);
    }
}
