package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private Long prodOptId; // 상품 ID
    private String productName; // 상품명
    private Long prodOriginPrice;
    private Long prodSalesPrice; // 상품 가격
    private Long quantity; // 주문 수량
    private String color; // 상품 색상
    private String size; // 상품 크기
    private String prodMainImgPath;
    private Long itemAmount; //제품별 총액(제품 판매 가격 * 수량)
//    private Long orderTotalOriginPrice; // 주문할 제품 전체에 대한 총액
//    private Long discountAmount;  // 할인 된 금액
//    private Long totalOrderPayment; // TotalAmount - 할인금액 - 포인트 = 총 결제금액
}