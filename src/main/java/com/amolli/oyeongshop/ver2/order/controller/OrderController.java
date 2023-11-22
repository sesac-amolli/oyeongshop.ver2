package com.amolli.oyeongshop.ver2.order.controller;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.order.service.OrderService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/to-order")
    //public String orderAdd(Model model, @RequestBody OrderItemDto orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails){
    public String orderAdd(Model model, @ModelAttribute("orderItemDTO") OrderItemDTO orderItemDto, @AuthenticationPrincipal PrincipalDetails userDetails) {

//        OrderDTO preparedOrderDTO = orderService.setPreparedOrderDto(orderItemDto);

        OrdersDTO ordersDTO = orderService.setOrdersDTO(orderItemDto);

        String userId = userDetails.getUser().getUserId();

        OrderUserDTO orderUserDto = orderService.setOrderUserDto(userDetails);

        model.addAttribute("orderUser", orderUserDto);
        model.addAttribute("Items", ordersDTO);

        return "/order/order";
    }

    @PostMapping(value="/to-orders")
    public String orderAdds(Model model, @RequestParam List<Long> selectedItems, @AuthenticationPrincipal PrincipalDetails userDetails){



        OrdersDTO ordersDTO = orderService.setOrdersDTO(selectedItems);

        String userId = userDetails.getUser().getUserId();

        OrderUserDTO orderUserDto = orderService.setOrderUserDto(userDetails);

        model.addAttribute("orderUser", orderUserDto);
        model.addAttribute("Items", ordersDTO);

        return "/order/order";
    }

    // DB에 주문 내역 생성
    @PostMapping(value = "/create", produces = "application/json")
    @ResponseBody
    public String order(@RequestBody OrderPayDto orderPayDto,
                        RedirectAttributes redirectAttributes,
                        Model model, @AuthenticationPrincipal PrincipalDetails userDetails){

        System.out.println(orderPayDto);

        String userId = userDetails.getUser().getUserId();
        List<OrderItemDTO> orderItemsDTO = orderPayDto.getOrderItemDTOs();
        OrderDeliveryDTO orderDeliveryDTO = orderPayDto.getOrderDeliveryDTO();
        OrderPriceDTO orderPriceDTO = orderPayDto.getOrderPriceDTO();

        // ToDo 여기에 필요한 유효성 검사 및 비즈니스 로직 수행

        // 주문 서비스를 호출하여 주문 처리
        Long orderId = orderService.order(orderItemsDTO, orderDeliveryDTO, orderPriceDTO, userId);
        return orderId+"";
    }

    // 주문 상세내역 보기
    @GetMapping("/order-detail/{orderId}")
    public String orderDetails(Model model, @PathVariable Long orderId, @AuthenticationPrincipal PrincipalDetails userDetails) {

        OrderResponseDTO orderResponseDTO = orderService.setOrderResponseDTO(orderId);
        OrderDetailsResponseDTO orderDetailsResponseDTO = orderService.setOrderDetailResponseDTO(orderId);

        model.addAttribute("Order", orderResponseDTO);
        model.addAttribute("OrderDetails", orderDetailsResponseDTO);

        return "/order/order-detail";
    }


    @GetMapping("/order-list")
    public void orderLists(@PathVariable(required = false) Integer page, Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("주문내역조회");

        // page가 null인 경우 기본값으로 0을 사용
        int pageNumber = (page != null) ? page : 0;

        String userId = userDetails.getUser().getUserId();
        List<OrderListDTO> orderListDTOS = orderService.setOrderListDTOList(userId);
        for(OrderListDTO orderListDTO : orderListDTOS){
            System.out.println(orderListDTO);
        }

        model.addAttribute("orderList", orderListDTOS);

    }


    @GetMapping("/return-list")
    public String returnList() {
        return "/order/return-list";
    }
}
