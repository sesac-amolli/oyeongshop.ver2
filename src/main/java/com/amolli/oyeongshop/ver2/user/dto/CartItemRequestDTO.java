package com.amolli.oyeongshop.ver2.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CartItemRequestDTO {

    private Long prodId;
    private String color;
    private String size;
    private int quantity;
    private Long prodSalesPrice;
    private Long userId;

    public CartItemRequestDTO(Long prodId, String color, String size, int quantity, Long prodSalesPrice, Long userId) {
        this.prodId = prodId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.prodSalesPrice = prodSalesPrice;
        this.userId = userId;
    }
}
