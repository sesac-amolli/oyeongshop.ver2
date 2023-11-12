package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CartDTO {

    private Long cartItemId;
//    private LocalDate cartItemDate;
    private Long cartItemAmount;
    private Long cartId;
    private Long prodOptId;


    public CartDTO(Long cartItemId, /*LocalDate cartItemDate,*/ Long cartItemAmount, Long cartId, Long prodOptId) {
        super();
        this.cartItemId = cartItemId;
//        this.cartItemDate = cartItemDate;
        this.cartItemAmount = cartItemAmount;
        this.cartId = cartId;
        this.prodOptId = prodOptId;
    }

    public CartItem toEntity(){
        return CartItem.builder()
//                .cartItemDate(getCartItemDate())
                .cartItemAmount(getCartItemAmount())
                .build();
    }
}
