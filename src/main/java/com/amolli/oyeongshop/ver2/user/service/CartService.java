package com.amolli.oyeongshop.ver2.user.service;

//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.User;

import java.util.List;

public interface CartService {
    void addCart(CartItemRequestDTO cartItemRequestDTO, String userId);
    void modifyCart(List<CartItemRequestDTO> cartItemRequestDTOS,String userId);

    void deleteCart(List<Long> cartItemIds,String userId);

    Cart viewCartList(String userId);

}
