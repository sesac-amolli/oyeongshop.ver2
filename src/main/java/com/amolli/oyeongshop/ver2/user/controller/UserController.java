package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

import com.amolli.oyeongshop.ver2.user.dto.WishListDTO;
import com.amolli.oyeongshop.ver2.user.dto.WishListResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.service.CartService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        Cart cart = cartService.viewCartList(userId);
        System.out.println("cart controller@!#!@#@@@" + cart);

        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        System.out.println("cart responseDTO@!#!@#@@@" + cart);

        model.addAttribute("cartDTO", cartResponseDTO.from(cart));
        System.out.println("cart model@!#!@#@@@" + cart);

        return "/user/cart";
    }

    // 장바구니 수량 수정
    @PostMapping("/cart/modify")
    public String modifyCart(@RequestBody List<CartItemRequestDTO> cartItemRequestDTOS, @AuthenticationPrincipal PrincipalDetails userDetails){
        String userId = userDetails.getUser().getUserId();
        cartService.modifyCart(cartItemRequestDTOS, userId);

        return "redirect:/user/cart/list";
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
    public String wishlist(@AuthenticationPrincipal PrincipalDetails details, Long wishListId, Model model){

        List<Wishlist> wishlists = userService.findMyWishList(details);

        List<WishListResponseDTO> wishListDTOS = wishlists.stream().map(WishListResponseDTO::from).collect(Collectors.toList());

        model.addAttribute("wishListDTOS", wishListDTOS);

        return "/user/wishlist";
    }

    @ResponseBody
    @PostMapping("/wishlist/{prodId}")
    public String uploadWishList(@AuthenticationPrincipal PrincipalDetails details,
                                 @PathVariable Long prodId, WishListDTO wishListDTO){
        System.out.println("prodId"+prodId);
        Long wishListId = userService.uploadWish(details, prodId, wishListDTO);

        return wishListId+"";
    }


    @ResponseBody
    @PostMapping("/wishlist-delete/{prodId}")
    public void deleteWishList(@PathVariable Long prodId, @AuthenticationPrincipal PrincipalDetails details){
        Long wishlistId = userService.findWishList(prodId, details);
        userService.deleteWishList(wishlistId);
        System.out.println("Controller~!~!~ wishlistId:::" + wishlistId);
    }


    @PostMapping("/wishlist-delete")
    public String deleteMyWishList(@RequestParam("wishProdId") Long prodId, @AuthenticationPrincipal PrincipalDetails details){
        Long wishlistId = userService.findWishList(prodId, details);
        userService.deleteWishList(wishlistId);
        System.out.println("Controller~!~!~ wishlistId:::" + wishlistId);
        return "redirect:/user/wishlist";
    }
}
