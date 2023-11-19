package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

@Data
public class OrderPriceDTO {
    private Long orderTotalOriginPrice;

    private Long totalOrderPayment;

    private Long orderTotalPayment;
}
