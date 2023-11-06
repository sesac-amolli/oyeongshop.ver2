package com.amolli.oyeongshop.ver2.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/orderlist")
    public String orderList(){
        return "/order/order-list";
    }

    @GetMapping("/returnlist")
    public String returnList(){
        return "/order/return-list";
    }

    @GetMapping("/toroder")
    public String toOrder(){
        return "/order/order";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(){
        return "/order/order-detail";
    }
}
