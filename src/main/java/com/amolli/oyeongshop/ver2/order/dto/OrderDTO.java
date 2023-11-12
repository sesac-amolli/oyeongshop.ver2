package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private Long productId; // 상품 ID
    private String productName; // 상품명
    private Long price; // 상품 가격
    private Long quantity; // 주문 수량
    private String color; // 상품 색상
    private String size; // 상품 크기

}