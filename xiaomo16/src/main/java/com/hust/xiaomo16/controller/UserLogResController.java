package com.hust.xiaomo16.controller;


import com.hust.xiaomo16.entity.UserInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.Map;
/**
 * @program: sell
 * @description: 用户登录注册
 * @create: 2019-01-24 13:45
 **/

@Controller
@RequestMapping("/user")
@Slf4j
public class UserLogResController {
    @Autowired
    private UserService us;

    @RequestMapping("/chat")
    public String toChat(){
        return "common/chat";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "buyer/index";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "common/login";
    }

    @PostMapping(value = "login")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        UserInfo u = us.findUser(username);
        if (u.getPassword().equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/user/chat";
        }
        /*if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }*/
        else {
            map.put("msg", "用户名密码错误");
            return "redirect:/user/login";
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String, Object> map) {
        UserInfo newUser = new UserInfo();
        newUser.setUserID();
        newUser.setUsername(username);
        newUser.setPassword(password);
        try{
            us.register(newUser);
        } catch (MyException e) {
            log.error("【用户注册】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/user/login");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.REGISTER_SUCCESS.getMessage());
        map.put("url", "/xiaomo/user/login");
        return new ModelAndView("common/success",map);
    }
}