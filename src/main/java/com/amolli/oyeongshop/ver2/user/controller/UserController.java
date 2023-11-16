package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.WishListDTO;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage")
    public String index( ) {

        return "/user/mypage";
    }

    @GetMapping("/cart")
    public String cart( ) {

        return "/user/cart";
    }

    @GetMapping("/myinfo")
    public String myinfo(){

        return "/user/myinfo";
    }

    @GetMapping("/wishlist")
    public String wishlist(){

        return "/user/wishlist";
    }

    @PostMapping("/wishlist")
    public String uploadWishList(@AuthenticationPrincipal PrincipalDetails details,
                                 @RequestParam("wishProdId") Long prodId, WishListDTO wishListDTO){

        userService.uploadWish(details, prodId, wishListDTO);

        return "redirect:/product/detail/" + prodId;
    }
}
