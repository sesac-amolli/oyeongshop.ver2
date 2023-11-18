package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.CartItem;
import lombok.*;

@Setter
@Getter
@Data
public class CartItemResponseDTO {

    private Long cartItemId;
    private String color;
    private String size;
    private int quantity;
    private Long prodSalesPrice;
    private String prodName;

    public CartItemResponseDTO(Long cartItemId, String color, String size, int quantity, Long prodSalesPrice, String prodName) {
        this.cartItemId = cartItemId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.prodSalesPrice = prodSalesPrice;
        this.prodName = prodName;

    }

    public static CartItemResponseDTO from(CartItem cartItem){
        final Long cartItemId = cartItem.getId();
        final String color = cartItem.getProductOption().getProdOptColor();
        final String size = cartItem.getProductOption().getProdOptSize();
        final int quantity = cartItem.getCartItemAmount();
        final Long prodSalesPrice = cartItem.getProductOption().getProduct().getProdSalesPrice();
        final String prodName = cartItem.getProductOption().getProduct().getProdName();
        return new CartItemResponseDTO(cartItemId, color, size, quantity, prodSalesPrice, prodName);
    }


}
