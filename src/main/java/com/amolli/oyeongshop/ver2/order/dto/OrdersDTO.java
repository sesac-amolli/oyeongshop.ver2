package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersDTO {

    private List<OrderDTO> orders;
    private Long orderTotalOriginPrice; // 주문할 제품 전체에 대한 총액
    private Long discountAmount;  // 할인 된 금액
    private Long totalOrderPayment; // TotalAmount - 할인금액 - 포인트 = 총 결제금액

}
