package com.hust.xiaomo16.controller;
import com.alibaba.fastjson.JSONObject;
import com.hust.xiaomo16.entity.ChatRecord;
import com.hust.xiaomo16.entity.ReservationDetail;
import com.hust.xiaomo16.entity.UserInfo;
import com.hust.xiaomo16.service.ChatService;
import com.hust.xiaomo16.service.ReservationService;
import com.hust.xiaomo16.service.UserService;
import com.hust.xiaomo16.utils.KeyUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

@Controller

public class ChatRecordController {

    @Autowired
    private ChatService cs1;


    @Autowired
    private UserService us;

    @Autowired
    private ReservationService rs;

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("msg", "SpringBoot Ajax 示例");

        return "buyer/chat";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public String home() {

        return "home";
    }
//    @RequestMapping(value = "/data", method = RequestMethod.POST)
//    @ResponseBody
//    public List<User> data() {
//        List<User> list = new ArrayList<>();
//System.out.println("here");
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setId(i + 1);
//            user.setName("springboot" + i);
//            user.setSex("male");
//            user.setAge(i + 1);
//            user.setRole("developer");
//
//            list.add(user);
//        }
//
//        return list;
//    }
    @RequestMapping(value="/user/update")
    @ResponseBody
    public Object update(@RequestBody JSONObject params, HttpSession session) throws Exception {

        String username = (String) session.getAttribute("loginUser");
        UserInfo u = us.findUser(username);
        Map<String, Object> map = new HashMap<>();
        String userInput = params.getString("userInput");
        LinkedList<String> linkedList = (LinkedList<String>) session.getAttribute("linkedList");

        if(userInput.equals("我要预约")||userInput.equals("预定") ||userInput.equals("预约")||userInput.equals("我要预定") ){
            linkedList.add(userInput);
            session.setAttribute("linkedList", linkedList);
            map.put("success", true);
            map.put("message", "您要预约2人桌还是4人桌？" );
            return map;
        }
        if ((userInput.equals("2人桌") || userInput.equals("四人桌") || userInput.equals("两人桌") ||
                userInput.equals("4人桌") || userInput.equals("2")
                || userInput.equals("4")) && (linkedList.getLast().equals("我要预约") ||
                linkedList.getLast().equals("我要预定")
                || linkedList.getLast().equals("预约") || linkedList.getLast().equals("预定"))) {
            int tableType = 0;
            if (userInput.equals("2人桌") || userInput.equals("两人桌") || userInput.equals("2")) {
                tableType = 2;
            } else {
                tableType = 4;
            }
            linkedList.add(userInput);
            session.setAttribute("linkedList", linkedList);
            ReservationDetail reservationDetail = new ReservationDetail();
            int queNum = rs.findTheLast(Integer.valueOf(tableType))+1;
            reservationDetail.setQueNum(queNum);
            reservationDetail.setReservaTime(new Date());
            reservationDetail.setReservaId(KeyUtil.genUniqueKey());
            reservationDetail.setStatus("排队中");
            reservationDetail.setTableType(Integer.valueOf(tableType));
            reservationDetail.setUserId(u.getUserID());
            rs.create(reservationDetail);
            map.put("success", true);
            map.put("message", "预约成功！您的排队号码是" + queNum);
            return map;
        }
        else{
            linkedList.add(userInput);
            session.setAttribute("linkedList", linkedList);

            ChatRecord c1=new ChatRecord();
            ChatRecord c2=new ChatRecord();
            c1.setChatTime(new Date());
            c1.setIsRobot(0);
            c1.setSentenceId(KeyUtil.genUniqueKey());
            String result = "";
            c1.setSentenceInfo(userInput);
            c1.setUserId(u.getUserID());
            cs1.save(c1);

            Process process = Runtime.getRuntime().exec("python /Users/boon/Downloads/QQ下载/xiaoMo06/work/xiaoMoAI.py " + userInput);
            InputStreamReader ir = new InputStreamReader(process.getInputStream(), "UTF-8");
            LineNumberReader input = new LineNumberReader(ir);

            ChatRecord robot = new ChatRecord();
            robot.setChatTime(new Date());
            robot.setUserId(u.getUserID());
            robot.setSentenceId(KeyUtil.genUniqueKey());
            robot.setIsRobot(1);

            while (result != null) {
                result = input.readLine();
                if (result != null) {
                    robot.setSentenceInfo(result);
                    cs1.save(robot);
                    System.out.println(result);
                    map.put("success", true);
                    map.put("message",result);
                }
            }
            input.close();
            ir.close();
            process.waitFor();

            //@RequestBody只能给方法的参数注解，表明一次接收到请求参数
            //JSONObject为alibaba的fastjson包下类，推荐使用。另外也可以使用String来接收前端的json格式传参。
//        Integer id = params.getInteger("userId");
//
////        System.out.println(id);

            //以上就是获取到参数体内id和name。后面再加上自己的业务逻辑即可。如果是dubbo方式的consumer，请将service注入进来，直接调用即可

            return map;
        }
    }


}
