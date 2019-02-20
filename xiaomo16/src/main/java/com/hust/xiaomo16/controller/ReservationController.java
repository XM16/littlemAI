package com.hust.xiaomo16.controller;



import com.hust.xiaomo16.entity.ReservationDetail;
import com.hust.xiaomo16.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService rs;
    /**添加预约记录*/
    @PostMapping("/add")
    public String add_reservation(Model model, @ModelAttribute ReservationDetail reservationDetail){
        ReservationDetail rd=new ReservationDetail();
        rd.setQueNum(reservationDetail.getQueNum());
        rd.setReservaTime(reservationDetail.getReservaTime());
        rd.setReservaId(reservationDetail.getReservaId());
        rd.setStatus(reservationDetail.getStatus());
        rd.setTableType(reservationDetail.getTableType());
        rd.setUserId(reservationDetail.getUserId());
        rs.create(rd);
        model.addAttribute("reservation",rd);
        return "success";
    }
    /**获取排队状态*/
    @GetMapping("/getStatus")
    public String get_status(Model model, @PathVariable("tableType") Integer tableType){
        Integer num=rs.findOne(tableType);
        model.addAttribute("presentNum",num);
        return "status";
    }
    /**获取预约队列*/
    @GetMapping("/get")
    public ModelAndView get(Map<String,Object> map){
        List<ReservationDetail> rds=rs.findList();
        map.put("reservationList",rds);
        return new ModelAndView("reservation/list",map);
    }
    /**获取删除界面*/
    @GetMapping("/getDelete")
    public ModelAndView getDelete(Map<String,Object> map){
        List<ReservationDetail> rds=rs.findList();
        map.put("reservationList",rds);
        return new ModelAndView("reservation/getDelete",map);
    }
    @RequestMapping("/getList")
    public ModelAndView getList(Map<String,Object> map){
        List<ReservationDetail> rds=rs.findList();
        map.put("reservationList",rds);
        return new ModelAndView("reservation/list",map);
    }
    /**更新排队情况*/
    @PostMapping("/update/{id}")
    public ModelAndView update(Model model, @PathVariable("id") Integer id, Map<String,Object> map){
         rs.cancel(Integer.toString(id));
         List<ReservationDetail> rds=rs.findList();
        map.put("reservationList",rds);
        return new ModelAndView("reservation/list",map);
    }
    /**删除预约*/
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "userId",required = false) String id){

        rs.cancel(id);

        return "redirect:/reservation/getList";

    }
    /**进入修改界面*/
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) String reservaId,
                              Map<String,Object> map){
        if(reservaId!=null){
            ReservationDetail reservationDetail=rs.findReservation(reservaId);
            map.put("reservationDetail",reservationDetail);
        }
        return new ModelAndView("reservation/index",map);
    }
/**保存更改*/
@PostMapping(value = "/save")
public String save(@RequestParam("reservaId") String reservaId,
                    @RequestParam("userId") String userId,
                   @RequestParam("reserTime") String reserTime,
                   @RequestParam("queNum") String queNum,
                   @RequestParam("status") String status,
                   @RequestParam("tableType") String tableType,
                    Map<String, Object> map) throws Exception{
ReservationDetail rd=new ReservationDetail();
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
rd.setReservaTime(sdf.parse(reserTime));
rd.setQueNum(Integer.valueOf(queNum));
rd.setStatus(status);
rd.setUserId(userId);
rd.setTableType(Integer.valueOf(tableType));
rd.setReservaId(reservaId);
rs.updateStatus(rd);
return "redirect:/reservation/getList";
}
}
