package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.User;
import com.hust.xiaomo16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService us;
    @PostMapping(value="/user/login")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        User u=us.findUser(username);
        if(u.getPassword().equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        /*if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }*/else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
