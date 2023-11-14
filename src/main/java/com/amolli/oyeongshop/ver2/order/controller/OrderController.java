package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order-list")
    public String orderList(){
        return "/order/order-list";
    }

    @GetMapping("/return-list")
    public String returnList(){
        return "/order/return-list";
    }

    @GetMapping("/to-order")
    public String orderAdd(Model model){

        OrderDto orderDTO = new OrderDto();

        model.addAttribute("orderItem", orderDTO);

        return "/order/order";
    }

    @PostMapping(value = "/create-order")
    public String order(@ModelAttribute("orderDetailsDTO") OrderDetailsDto orderDetailsDTO,
                        @ModelAttribute("orderDeliveryDTO") OrderDeliveryDto orderDeliveryDTO,
                        Model model) {

        String userId = "guest";

        System.out.println(orderDetailsDTO);
        System.out.println(orderDeliveryDTO);

        // 여기에서 필요한 유효성 검사 및 비즈니스 로직 수행
        // orderDTO와 orderDeliveryDTO를 사용하여 주문을 처리

        // 주문 서비스를 호출하여 주문 처리
        Long orderId = orderService.order(orderDetailsDTO, orderDeliveryDTO, userId);
//
//        // 주문 완료 페이지로 이동 또는 추가적인 작업 수행
//        model.addAttribute("orderId", orderId);
        return "/order/order-detail";

    }


    @GetMapping("/order-detail")
    public String orderDetail(){

        return "/order/order-detail";
    }
}
