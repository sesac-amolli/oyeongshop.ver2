package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

@Data
public class OrderDetailResponseDTO {

    private String prodMainImgPath;
    private String productName;
    private Long prodOriginPrice;
    private Long prodSalesPrice; // 상품 가격
    private Long quantity; // 주문 수량
    private String color; // 상품 색상
    private String size; // 상품 크기
    private Long itemAmount;
    private Long prodId;
}
