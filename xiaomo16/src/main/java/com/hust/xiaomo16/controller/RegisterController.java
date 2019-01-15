package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.User;
import com.hust.xiaomo16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserService us;
@PostMapping(value="/user/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String,Object> map){
    User newUser=new User();
    newUser.setUserID();
    newUser.setUsername(username);
    newUser.setPassword(password);
    us.register(newUser);
    return "login";
}

}
