package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemResponseDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.service.CartService;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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

        return "redirect:/user/cart/list";
    }

    // 장바구니 담긴 상품 보여주기
    @GetMapping("/cart/list")
    public String viewCartList(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
        String userId = userDetails.getUser().getUserId();
//        User user = userService.getUserById(userId);
        Cart cart = cartService.viewCartList(userId);
//        User user = userService.getUserById(userId);
        System.out.println("cart controller@!#!@#@@@" + cart);

        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        System.out.println("cart responseDTO@!#!@#@@@" + cart);


        model.addAttribute("cartDTO", cartResponseDTO.from(cart));
        System.out.println("cart model@!#!@#@@@" + cart);
//        List<CartResponseDTO> cartResponseDTO = cart.stream()
//                        .filter(CartResponseDTO::from)
//                        .collect(toList());
//                builder().user(user).build();

//        List<CartResponseDTO> cartResponseDTOS = cart.stream()
//                .map(CartItemResponseDTO::from)
//                .collect(Collectors.toList());

//        CartResponseDTO cartResponseDTO = cart.builder()
//                List<CartItemResponseDTO> cartItemResponseDTOS = cart.getCartItems().stream()
//                .map(CartItemResponseDTO::from)
//                .collect(Collectors.toList());

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
