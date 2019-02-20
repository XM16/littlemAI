package com.hust.xiaomo16.controller;
import com.hust.xiaomo16.entity.OrderDetail;
import com.hust.xiaomo16.entity.OrderMaster;
import com.hust.xiaomo16.entity.ProductInfo;
import com.hust.xiaomo16.entity.UserInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.service.BuyerOrderService;
import com.hust.xiaomo16.service.ProductService;
import com.hust.xiaomo16.service.UserService;
import com.hust.xiaomo16.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/buy/order")
@Slf4j
public class BuyController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuyerOrderService buyerOrderService;

    @PostMapping(value = "/guodu")
    public ModelAndView guodu(@RequestParam("productName") String productName,
                              @RequestParam("productId") String productId,
                                 Map<String, Object> map, HttpSession session) {
        session.setAttribute("productName",productName);
        session.setAttribute("productId",productId);
        return new ModelAndView("buyer/order", map);
    }

    @PostMapping(value = "/add")
    public ModelAndView add(@RequestParam("buyerName") String buyerName,
                                 @RequestParam("buyerPhone") String buyerPhone,
                                 Map<String, Object> map, HttpSession session) {

        String productName=(String) session.getAttribute("productName");
        String username=(String) session.getAttribute("loginUser");
        String productId=(String) session.getAttribute("productId");
        UserInfo user=userService.findUser(username);
        OrderDetail orderDetail=new OrderDetail();
        OrderMaster orderMaster=new OrderMaster();
        ProductInfo productInfo=productService.findOne(productId);
        String orderId=KeyUtil.genUniqueKey();
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setOrderId(orderId);
        orderDetail.setProductId(productId);
        orderDetail.setProductName(productName);
        orderDetail.setProductPrice(productInfo.getProductPrice());
        orderDetail.setProductIcon(productInfo.getProductIcon());
        orderDetail.setProductQuantity(1);
        orderMaster.setOrderAmount(productInfo.getProductPrice());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster.setBuyerName(buyerName);
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(1);
        orderMaster.setBuyerPhone(buyerPhone);
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerUserid(user.getUserID());
        try{
            buyerOrderService.addOrderDetail(orderDetail);
            buyerOrderService.addOrderMater(orderMaster);
        }catch (MyException e) {
            log.error("购买失败", e);
            map.put("msg", e.getMessage());
            map.put("url", "/xiaomo/user/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.BUY_SUCCESS.getMessage());
        map.put("url", "/xiaomo/user/index");

        return new ModelAndView("common/success", map);
    }
}
