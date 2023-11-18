package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class OrderItemDto {

    private Long prodId; // 상품 ID
    private String color;
    private String size;
    private Long quantity; // 주문 수량
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private Long prodOptId;

}
