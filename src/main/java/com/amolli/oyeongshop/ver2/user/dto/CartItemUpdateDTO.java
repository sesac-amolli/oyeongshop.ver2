package com.amolli.oyeongshop.ver2.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemUpdateDTO {
    private Long cartItemId;
    private int quantity;

    public CartItemUpdateDTO(Long cartItemId, int quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }

    // Standard getters and setters
}
