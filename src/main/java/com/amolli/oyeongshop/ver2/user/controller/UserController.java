package com.amolli.oyeongshop.ver2.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

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
    public String uploadWishList(){

        return "/user/wishlist";
    }
}
