package com.hust.xiaomo16.controller;


import com.hust.xiaomo16.entity.ReservationDetail;
import com.hust.xiaomo16.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        rd.setReserTime(reservationDetail.getReserTime());
        rd.setReservaID(reservationDetail.getReservaID());
        rd.setStatus(reservationDetail.getStatus());
        rd.setTableType(reservationDetail.getTableType());
        rd.setUserID(reservationDetail.getUserID());
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
    public  String get(Model model){
        List<ReservationDetail> rds=rs.findList();
        model.addAttribute("Rds",rds);
        return "reservaQue";
    }
    /**更新排队情况*/
    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Integer id, ReservationDetail rd){
         rd.setUserID(Integer.toString(id));
         rs.updateStatus(rd);
         List<ReservationDetail> rds=rs.findList();
         model.addAttribute("Rds",rds);
         return "update";

    }

}
