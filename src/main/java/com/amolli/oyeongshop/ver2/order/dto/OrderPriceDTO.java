package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPriceDTO {
    private Long orderTotalOriginPrice;

    private Long totalOrderPayment;

    private Long orderTotalPayment;
}
