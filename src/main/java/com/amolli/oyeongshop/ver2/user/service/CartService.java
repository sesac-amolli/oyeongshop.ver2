package com.amolli.oyeongshop.ver2.user.service;

//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public interface CartService {
    void addCart(Long prodOptId, int amount, UserDetails userDetails);

}
