package com.amolli.oyeongshop.ver2.order.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//상품 상세 페이지에서 선택한 사품 정보 담아오는 DTO
public class OrderItemDTO {

    private Long prodId; // 상품 ID
    private String color;
    private String size;
    private Long quantity; // 주문 수량
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private Long prodOptId;

}
