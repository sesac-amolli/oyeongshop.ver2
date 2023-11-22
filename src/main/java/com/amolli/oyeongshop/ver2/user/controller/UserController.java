package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

import com.amolli.oyeongshop.ver2.user.dto.*;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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

    // 마이페이지
    @GetMapping("/mypage")
    public String showMypage(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
        Long userPoint = userDetails.getUser().getUserPoint();
        model.addAttribute("point", userPoint);
        return "/user/mypage";
    }

    // 내 정보 조회
    @GetMapping("/myinfo")
    public String myinfo(){

        return "/user/myinfo";
    }

    // 포인트 내역 조회
    @GetMapping("/point")
    public String myPoint(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        Long userPoint = userDetails.getUser().getUserPoint();
        model.addAttribute("point", userPoint);

        List<PointDto> pointDtos = userService.myPoint(userDetails.getUser().getUserId());
        model.addAttribute("points", pointDtos);
        return "/user/point";
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
        List<CartItemResponseDTO> cartItems = cartService.viewCartList(userId);

        model.addAttribute("cartItems", cartItems);

        return "/user/cart";
    }

    // 장바구니 수량 수정
//    @PostMapping("/cart/modify")
//    public String modifyCart(@ModelAttribute List<CartItemUpdateDTO> cartItemUpdateDTOS, @AuthenticationPrincipal PrincipalDetails userDetails){
//        String userId = userDetails.getUser().getUserId();
//        System.out.println("Cart수정");
//        cartService.modifyCart(cartItemUpdateDTOS, userId);
//        System.out.println("wrapper" + cartItemUpdateDTOS);
//        return "redirect:/user/cart/list";
//    }

    @PostMapping("/cart/modify")
    public String modifyCart(@RequestParam(value = "cartItemId1") Long cartItemId
            , @RequestParam(value = "quantity1") int quantity
            , @AuthenticationPrincipal PrincipalDetails userDetails
                             , @RequestBody CartItemUpdateDTO cartItemUpdateDTO){
        String userId = userDetails.getUser().getUserId();
        System.out.println("Cart수정");
        System.out.println("quantity1!!!!:" + quantity);
        System.out.println("cartItemId!!!!:" + cartItemId);
        cartService.modifybyCartId(cartItemId, cartItemUpdateDTO);
//        cartService.modifyCart(cartItemUpdateDTO, userId);
        System.out.println("wrapper" + cartItemUpdateDTO);
        return "redirect:/user/cart/list";
    }

    @PostMapping("/cart/delete")
    public String deleteCart(@RequestParam List<Long> cartItemIds, @AuthenticationPrincipal PrincipalDetails userDetails){
        String userId = userDetails.getUser().getUserId();
        System.out.println("Cart삭제");

        cartService.deleteCart(cartItemIds, userId);

        for(Long l : cartItemIds) {
            System.out.println(l);
        }
//        cartService.deleteCart(cartItemIds, userId);

        return "redirect:/user/cart/list";
    }


    // 장바구니 선택 상품 주문하기
    @PostMapping("/cart/order")
    public String orderCart(){

        return "redirect://오더페이지";
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
