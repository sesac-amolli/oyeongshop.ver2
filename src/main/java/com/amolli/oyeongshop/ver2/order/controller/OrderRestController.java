package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/api")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/orderlist")
    public String orderList(){
        return "/order/order-list";
    }

    @GetMapping("/returnlist")
    public String returnList(){
        return "/order/return-list";
    }

    @GetMapping("/orderadd")
    public String orderAdd(Model model){
        model.addAttribute("order", Order.builder().build());
        return "/order/order";
    }

    @PostMapping("/orderadd")
    public String orderSave(@Validated Order order, BindingResult result){
        if(result.hasErrors()){
            System.out.println(order);
            System.out.println(result);

            return "/login";
        }else {
            System.out.println(order);
            Order savedOrder = orderService.save(order);

            System.out.println("saved:" +  savedOrder);
            System.out.println(result);
            return "/";
        }
        //결제랑 연결하기 전에 우선 테스트하는 동안은 메인으로 이동
    }

//    @PostMapping("/crOrder")
//    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
//        try {
//            OrderUserDTO orderUserDTO = orderRequestDTO.getOrderUserDTO();
//            List<OrderDetailsDto> orderDetailsDTOS = orderRequestDTO.getOrderDetailsDTOS();
//            OrderDeliveryDto orderDeliveryDTO = orderRequestDTO.getOrderDeliveryDTO();
//
//            List<OrderDetail> orderDetails = orderService.convertToOrderDetails(orderDetailsDTOS);
//
//            Order order = Order.createOrder(orderUserDTO, orderDetails, orderDeliveryDTO);
//
//            // 주문 생성 성공시
//            return ResponseEntity.ok("주문이 생성되었습니다.");
//        } catch (Exception e) {
//            // 주문 생성 실패시
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 생성에 실패했습니다.");
//        }
//    }

    @GetMapping("/orderDetail")
    public String orderDetail(){

        return "/order/order-detail";
    }
}
