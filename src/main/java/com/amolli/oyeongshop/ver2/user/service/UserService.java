package com.amolli.oyeongshop.ver2.user.service;


import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.WishListDTO;
import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;

import java.util.Optional;

public interface UserService {
    public void signUp(UserDTO userDto);
    public User getUserById(String userId);


    Long uploadWish(PrincipalDetails userDetails, Long prodId, WishListDTO wishListDTO);
    Long findWishList(Long prodId, PrincipalDetails details);
    void deleteWishList(Long wishListId);

    boolean checkId(String id);
}
