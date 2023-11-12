package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;

public interface CartService {
    void uploadDB(Cart cart, CartDTO cartDTO, Long prodOptId);

//    void create(CartCreateRequestDTO req, User user);

    Long addCart(CartItemDTO cartItemDTO, String email);

}
