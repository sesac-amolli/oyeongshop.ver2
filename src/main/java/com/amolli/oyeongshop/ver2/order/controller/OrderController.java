package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping(value = "/to-order")
    //public String orderAdd(Model model, @RequestBody OrderItemDto orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails){
    public String orderAdd(Model model, OrderItemDto orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("ddd = "+orderItemDto);


        OrderDto preparedOrderDTO = orderService.setPreparedOrderDto(orderItemDto);

        String userId = userDetails.getUser().getUserId();

        OrderUserDto orderUserDto = orderService.setOrderUserDto(userId);
        System.out.println("PreparedOrderDTO : "+preparedOrderDTO);

        model.addAttribute("orderUser", orderUserDto);
        model.addAttribute("orderItem", preparedOrderDTO);

       // return "forward:/order/create-order";
       return "/order/order";
    }

    @RequestMapping(value = "/create-order")
    public String order(OrderDetailsDto orderDetailsDTO,
                        OrderDeliveryDto orderDeliveryDTO,
                        Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {

        String userId = userDetails.getUser().getUserId();

        System.out.println("orderDetailsDTO" + orderDetailsDTO);
        System.out.println("orderDeliveryDTO" + orderDeliveryDTO);

        // 여기에서 필요한 유효성 검사 및 비즈니스 로직 수행

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
