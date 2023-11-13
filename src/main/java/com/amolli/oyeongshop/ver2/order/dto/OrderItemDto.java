package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class OrderItemDto {


    private Long productOptId; // 상품 옵션 ID
    private Long price; // 상품 가격
    private Long quantity; // 주문 수량

}
