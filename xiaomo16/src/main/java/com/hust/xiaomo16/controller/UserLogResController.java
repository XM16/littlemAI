package com.hust.xiaomo16.controller;


import com.hust.xiaomo16.entity.ProductInfo;
import com.hust.xiaomo16.entity.UserInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.service.ProductService;
import com.hust.xiaomo16.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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

    @Autowired
    private ProductService ps;

    @RequestMapping("/chat")
    public String toChat(){

        return "buyer/chat";

    }

    @RequestMapping("/index")
    public ModelAndView toIndex(@RequestParam(value = "page" ,defaultValue="1") Integer page,
                                @RequestParam(value = "size",defaultValue = "16") Integer size,
                                Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=ps.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
//        List<ProductInfo> productList=ps.findUpAll();
//        map.put("productList",productList);
        return new ModelAndView("buyer/index",map);
    }

    @GetMapping("/login")
    public ModelAndView logResPage(){
        return new ModelAndView("common/login");
    }
    @PostMapping(value = "login")
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        UserInfo u = us.findUser(username);
        LinkedList<String> linkedList=new LinkedList();
        linkedList.add("test");
        if (u.getPassword().equals(password)) {
            session.setAttribute("loginUser", username);
            session.setAttribute("linkedList",linkedList);
            return "redirect:/user/chat";
//            return "redirect:/user/index";
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
    public ModelAndView register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String, Object> map) {
        UserInfo newUser = new UserInfo();
        newUser.setUserID();
        newUser.setUsername(username);
        newUser.setPassword(password);
       // us.register(newUser);

        try{
            us.register(newUser);
        } catch (MyException e) {
            log.error("用户注册失败", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/user/login");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.REGISTER_SUCCESS.getMessage());
        map.put("url", "/xiaomo/user/login");
        return new ModelAndView("common/success",map);

    }
}