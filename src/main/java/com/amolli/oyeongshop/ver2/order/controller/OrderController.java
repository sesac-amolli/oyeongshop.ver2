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
    public String order(@ModelAttribute @Valid OrderDetailsDTO orderDetailsDTO,
                        @ModelAttribute @Valid OrderDeliveryDTO orderDeliveryDTO,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류 처리
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("error", sb.toString());
            return "redirect:/main";
        }

        String userId = "guest";
        Long orderId;
        try {
            orderId = orderService.order(orderDetailsDTO, orderDeliveryDTO, userId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/main";
        }

        redirectAttributes.addFlashAttribute("success", "Order placed successfully! Order ID: " + orderId);
        return "redirect:/main";
    }


    @GetMapping("/orderDetail")
    public String orderDetail(){

        return "/order/order-detail";
    }
}
