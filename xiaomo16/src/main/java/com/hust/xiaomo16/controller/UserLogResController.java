package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.User;
import com.hust.xiaomo16.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
/**
 * @program: sell
 * @description: 用户登录注册
 * @create: 2019-01-24 13:45
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class UserLogResController {
    @Autowired
    private UserService us;

    @PostMapping(value = "login")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        User u = us.findUser(username);
        if (u.getPassword().equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        /*if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }*/
        else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String, Object> map) {
        User newUser = new User();
        newUser.setUserID();
        newUser.setUsername(username);
        newUser.setPassword(password);
        us.register(newUser);
        return "login";
    }
}