package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.service.CartService;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserService userService;

    @GetMapping("/mypage")
    public String index( ) {

        return "/user/mypage";
    }

    // 장바구니 상품 담기
    @PostMapping("/cart/add")
    public String addCart(CartItemRequestDTO cartItemRequestDTO, @AuthenticationPrincipal PrincipalDetails userDetails){
        String userId = userDetails.getUser().getUserId();
        cartService.addCart(cartItemRequestDTO, userId);

        return "/user/cart/list";
    }

    // 장바구니 담긴 상품 보여주기
    @GetMapping("/cart/list")
    public void viewCartList(@AuthenticationPrincipal PrincipalDetails userDetails) {
        String userId = userDetails.getUser().getUserId();
        User user = userService.getUserById(userId);
        cartService.viewCartList(user);
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
