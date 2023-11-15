package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final CartService cartService;

    @GetMapping("/mypage")
    public String index( ) {

        return "/user/mypage";
    }

    // 장바구니 상품 담기
    @PostMapping("/cart/add")
    public void addCart(@RequestParam Long prodOptId, @RequestParam int amount){
//        cartService.addCart(prodOptId, amount);

    }

    // 장바구니 담긴 상품 보여주기
    @GetMapping("/cart/list")
    public String cart( ) {

        return "/user/cart";
    }

    // 장바구니 선택 상품 주문하기
    @PostMapping("/cart/order")
    public String orderCart(){

        return "redirect://오더페이지";
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
