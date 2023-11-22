package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPriceDTO {
    // 상품 원가 합산 금액
    private Long orderTotalOriginPrice;
    // 상품의 판매가 합산 금액
    private Long orderTotalPayment;
    // 포인트 사용 후, 고객의 최종 결제 금액
    private Long totalOrderPayment;
}
