package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.entity.User;
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
    public void register(User user) {
        userRepository.save(user);
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
    public void update(User user) {
             userRepository.save(user);
    }

    @Override
    public User findUser(String username) {
        return userRepository.findOne(username);
    }
}
