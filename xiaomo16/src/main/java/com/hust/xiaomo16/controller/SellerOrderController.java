package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.converter.OrderForm2OrderDTOConverter;
import com.hust.xiaomo16.dto.OrderDTO;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.form.OrderForm;
import com.hust.xiaomo16.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;

/**
 * @program: xiaomo16
 * @description:卖家端
 * @author: Boon Guan
 * @create: 2019-01-14 15:22
 **/
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
//      orderDTOPage.getTotalPages();
        return new ModelAndView("order/list", map);
    }
    /**
    * @Description:  管理员添加订单
    * @Param:
    * @return:
    */
    @GetMapping("/addPage")
    public ModelAndView addPage(){
        return new ModelAndView("order/update");
    }

    @PostMapping("/add")
    public ModelAndView add(@RequestParam(value = "name") String  name,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "userId") String userId,
                            @RequestParam(value = "items") String items,
                             Map<String, Object> map) {
        OrderForm orderForm = new OrderForm();
        orderForm.setName(name);
        orderForm.setPhone(phone);
        orderForm.setUserId(userId);
        orderForm.setItems(items);
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        try {
            orderDTO = orderService.create(orderDTO);
        } catch (MyException e) {
            log.error("【卖家端新增订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/seller/order/list");
            return new ModelAndView("common/error", map);
        }
//        map.put("orderDTO",orderDTO);
        map.put("msg", ResultEnum.ORDER_ADD_SUCCESS.getMessage());
        map.put("url", "/xiaomo/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (MyException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/xiaomo/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (MyException e) {
            log.error("【卖家端订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }
    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (MyException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/xiaomo/seller/order/list");
        return new ModelAndView("common/success");
    }
}
