package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.ManagerInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.service.ManagerService;
import com.hust.xiaomo16.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerLogResController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/login")
    public ModelAndView logResPage(){
        return new ModelAndView("common/login");
    }

    @PostMapping(value = "login")
    public String login(@RequestParam("username") String mUsername,
                        @RequestParam("password") String mPassword,
                        Map<String, Object> map, HttpSession session) {
        ManagerInfo manager = managerService.findManager(mUsername);
        if (manager == null){
            map.put("msg","用户名不存在");
            return "login";
        }
        if (manager.getMPassword().equals(mPassword)) {
            session.setAttribute("loginUser", mUsername);
            return "redirect:/success";
        }
        /*if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }*/
        else {
            map.put("msg", "管理员密码错误");
            return "login";
        }
    }
    @PostMapping(value = "/register")
    public String register(@RequestParam("username") String mUsername,
                           @RequestParam("password") String mPassword,
                           Map<String, Object> map) {
        ManagerInfo manager = new ManagerInfo();
        manager.setManagerId(KeyUtil.genUniqueKey());
        manager.setMUsername(mUsername);
        manager.setMPassword(mPassword);
        ManagerInfo managerInfo = managerService.register(manager);
        if(managerInfo == null)
            map.put("msg","管理员注册失败");
        return "login";
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
