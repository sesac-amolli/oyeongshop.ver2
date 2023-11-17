package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
public class CartResponseDTO {

    private Long cartId;
    private List<CartItemResponseDTO> cartItemResponseDTOS;

    public CartResponseDTO(Long cartId, List<CartItemResponseDTO> cartItemResponseDTOS) {
        this.cartId = cartId;
        this.cartItemResponseDTOS = cartItemResponseDTOS;
    }

    public static CartResponseDTO from(Cart cart){
        final Long id = cart.getId();

        final List<CartItemResponseDTO> cartItemResponseDTOS = cart.getCartItems().stream()
                .map(CartItemResponseDTO::from)
                .collect(Collectors.toList());

        return new CartResponseDTO(id, cartItemResponseDTOS);
    }

}
