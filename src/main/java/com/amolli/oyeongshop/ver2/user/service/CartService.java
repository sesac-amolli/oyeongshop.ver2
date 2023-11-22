package com.amolli.oyeongshop.ver2.user.service;

//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemUpdateDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemUpdateWrapper;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.User;

import java.util.List;

public interface CartService {
    void addCart(CartItemRequestDTO cartItemRequestDTO, String userId);
//    void modifyCart(List<CartItemUpdateDTO> cartItemUpdates, String userId);
    void modifyCart(List<CartItemUpdateDTO> cartItemUpdateDTOS, String userId);

    void modifybyCartId(Long cartItemId, CartItemUpdateDTO cartItemUpdateDTO);
    void deleteCart(List<Long> cartItemIds,String userId);

    Cart viewCartList(String userId);

}
