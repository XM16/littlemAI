package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.ManagerInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.service.ManagerService;
import com.hust.xiaomo16.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: sell
 * @description: 管理员登录注册
 * @author: Boon Guan
 * @create: 2019-01-24 13:45
 **/
@Controller
@RequestMapping("/manager")
@Slf4j
public class ManagerLogResController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/houtai")
    public String toManager(){

        return "manager/main";

    }
    @GetMapping("/login")
    public ModelAndView logResPage(){
        return new ModelAndView("common/login");
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("musername") String mUsername,
                        @RequestParam("mpassword") String mPassword,
                        Map<String, Object> map, HttpSession session) {
        ManagerInfo manager = managerService.findManager(mUsername);
        System.out.println(mUsername);
        if (manager == null){
            map.put("msg","用户名不存在");
            if(mUsername=="jack")
            System.out.println("ok");
            return "login";
        }
        if (manager.getMPassword().equals(mPassword)) {
            session.setAttribute("loginUser", mUsername);
            return "redirect:/manager/houtai";
        }

        else {
            map.put("msg", "管理员密码错误");
            return "login";
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@RequestParam("musername") String mUsername,
                           @RequestParam("mpassword") String mPassword,
                           Map<String, Object> map) {
        ManagerInfo manager = new ManagerInfo();
        manager.setManagerId(KeyUtil.genUniqueKey());
        manager.setMUsername(mUsername);
        manager.setMPassword(mPassword);
//        ManagerInfo managerInfo = managerService.register(manager);
        try{
            managerService.register(manager);
        } catch (MyException e) {
            log.error("【管理员注册】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/manager/login");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.REGISTER_SUCCESS.getMessage());
        map.put("url", "/xiaomo/manager/login");
        return new ModelAndView("common/success",map);

    }

    @GetMapping("/list")
    public String listManager(Map<String, Object> map){
        List<ManagerInfo> managerInfoList = new ArrayList<>();
        try {
            managerInfoList = managerService.findManagerList();
        } catch (MyException e) {
            log.error("【查询管理员列表】管理员列表为空", e);
            return "manageInfoList";
        }
        map.put("managerInfoList",managerInfoList);
        return "manageInfoList";
    }

    @GetMapping("/findOne")
    public String findOneManager(Map<String, Object> map,
                                 @RequestParam("username")String mUsername){
        ManagerInfo manager = new ManagerInfo();
        try {
            manager = managerService.findManager(mUsername);
        } catch (MyException e){
            log.error("【查询单个管理员】管理员用户名不存在", e);
            return "manageInfoList";
        }
        map.put("manager",manager);
        return "managerInfo";
    }

    @DeleteMapping("/delete")
    public String deleteManager(Map<String, Object> map,
                                @RequestParam("username")String mUsername){
        managerService.delete(mUsername);
        map.put("msg","管理员删除成功");
        map.put("url", "/xiaomo/manager/list");
        return "common/success";

    }
}
