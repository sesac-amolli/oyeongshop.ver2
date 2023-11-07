package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
//@RestController
//@RequestMapping("/api/user")
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("/sign-up")
    public String signUp( ) {

        return "/user/signup";
    }

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
}
