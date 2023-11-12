package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

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
        OrderDTO orderDTO = new OrderDTO();

        model.addAttribute("productId", 1);
        model.addAttribute("productName", "Your Product");
        model.addAttribute("price", 10000);
        model.addAttribute("quantity", 2);
        model.addAttribute("color", "Red");
        model.addAttribute("size", "M");
        int totalPrice = 10000 * 2; // 가격 * 수량
        model.addAttribute("totalPrice", totalPrice);

        model.addAttribute("totalAmount", totalPrice);
        model.addAttribute("discountAmount", 0);
        model.addAttribute("totalPaymentAmount", totalPrice);

        model.addAttribute("orderItem", orderDTO);

        return "/order/order";
    }

    @PostMapping(value = "/crOrder")
    public String order(@ModelAttribute("orderDetailsDTO") OrderDetailsDTO orderDetailsDTO,
                        @ModelAttribute("orderDeliveryDTO") OrderDeliveryDTO orderDeliveryDTO,
                        Model model) {

        String userId = "guest";

        // 여기에서 필요한 유효성 검사 및 비즈니스 로직 수행
        // orderDTO와 orderDeliveryDTO를 사용하여 주문을 처리

        // 주문 서비스를 호출하여 주문 처리
        Long orderId = orderService.order(orderDetailsDTO, orderDeliveryDTO, userId);

        // 주문 완료 페이지로 이동 또는 추가적인 작업 수행
        model.addAttribute("orderId", orderId);
        return "/main";

    }


    @GetMapping("/orderDetail")
    public String orderDetail(){

        return "/order/order-detail";
    }
}
