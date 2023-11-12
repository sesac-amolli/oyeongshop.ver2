package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productOptionId;
    private String productName;
    private String productSize;
    private String productColor;
    private Long productPrice;
    private Long amount;

}
