package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import lombok.*;

@Setter
@Getter
@ToString
public class CartItemResponseDTO {

    private Long cartItemId;
    private String color;
    private String size;
    private int quantity;
    private Long prodSalesPrice;
    private Long prodOriginPrice;
    private String prodName;
    private String prodImg;

    @Builder
    public CartItemResponseDTO(Long cartItemId, String color, String size, int quantity,
                               Long prodSalesPrice, Long prodOriginPrice, String prodName, String prodImg) {
        this.cartItemId = cartItemId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.prodSalesPrice = prodSalesPrice;
        this.prodOriginPrice = prodOriginPrice;
        this.prodName = prodName;
        this.prodImg = prodImg;
    }

    public static CartItemResponseDTO from(CartItem cartItem, Product product){
        return CartItemResponseDTO.builder()
                .cartItemId(cartItem.getId())
                .color(cartItem.getProductOption().getProdOptColor())
                .size(cartItem.getProductOption().getProdOptSize())
                .quantity(cartItem.getCartItemAmount())
                .prodSalesPrice(product.getProdSalesPrice())
                .prodOriginPrice(product.getProdOriginPrice())
                .prodName(product.getProdName())
                .prodImg(product.getProdMainImgPath())
                .build();
    }


}
