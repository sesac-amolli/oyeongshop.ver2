package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/to-order")
    //public String orderAdd(Model model, @RequestBody OrderItemDto orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails){
    public String orderAdd(Model model, OrderItemDTO orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails){

//        OrderDTO preparedOrderDTO = orderService.setPreparedOrderDto(orderItemDto);

        OrdersDTO ordersDTO = orderService.setOrdersDTO(orderItemDto);

        String userId = userDetails.getUser().getUserId();

        OrderUserDTO orderUserDto = orderService.setOrderUserDto(userDetails);

        model.addAttribute("orderUser", orderUserDto);
        model.addAttribute("orderItem", ordersDTO);

       return "/order/order";
    }

    @PostMapping(value="/to-orders")
    public String orderAdds(Model model, @RequestParam List<Long> selectedItems, @AuthenticationPrincipal PrincipalDetails userDetails){

        //OrdersDto ordersDTO

        return "/order/order";
    }

    @RequestMapping(value = "/create-order")
    public String order(OrderItemsDTO orderItemsDTO,
                        OrderDeliveryDTO orderDeliveryDTO,
                        OrderPriceDTO orderPriceDTO,
                        Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {

        String userId = userDetails.getUser().getUserId();

        System.out.println("orderDetailsDTO" + orderItemsDTO);
        System.out.println("orderDeliveryDTO" + orderDeliveryDTO);

        // 여기에서 필요한 유효성 검사 및 비즈니스 로직 수행

        // 주문 서비스를 호출하여 주문 처리
        Long orderId = orderService.order(orderItemsDTO, orderDeliveryDTO, orderPriceDTO, userId);
//
//        // 주문 완료 페이지로 이동 또는 추가적인 작업 수행
//        model.addAttribute("orderId", orderId);
        return "/order/order-detail";

    }


    @GetMapping("/order-detail")
    public String orderDetail(){

        return "/order/order-detail";
    }



    @GetMapping("/order-list")
    public String orderList(){
        return "/order/order-list";
    }

    @GetMapping("/return-list")
    public String returnList(){
        return "/order/return-list";
    }
}
