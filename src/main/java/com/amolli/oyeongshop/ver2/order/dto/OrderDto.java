package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {


    private Long prodOptId = 1L; // 상품 ID
    private String productName = "검정셔츠"; // 상품명
    private Long price = 12300L; // 상품 가격
    private Long quantity = 3L; // 주문 수량
    private String color = "Blue"; // 상품 색상
    private String size = "M"; // 상품 크기
    private String prodMainImgPath = "/images/product/product-shirt1.jpg";
    private Long itemAmount = price*quantity;

}