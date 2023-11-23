package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.user.dto.*;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.CartRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;

    @Override
    public void addCart(CartItemRequestDTO cartItemRequestDTO, String userId) {


        Optional<User> user = userRepository.findById(userId);

        Cart cart = cartRepository.findByUser_UserId(userId);
        Long prodOptId = productOptionRepository.findProdOptIdByProdIdAndProdOptColorAndProdOptSize(
                cartItemRequestDTO.getProdId(),
                cartItemRequestDTO.getColor(),
                cartItemRequestDTO.getSize()
        );
        Optional<ProductOption> productOption = productOptionRepository.findById(prodOptId);

        if (productOption.isPresent()) {

            CartItem cartItem = CartItem
                    .builder()
                    .cart(cart)
                    .productOption(productOption.get())
                    .cartItemAmount(Math.toIntExact(cartItemRequestDTO.getQuantity()))
                    .build();

            cartItemRepository.save(cartItem);
        } else {
            // productOption이 존재하지 않는 경우의 처리
            // 예를 들면, 에러 메시지를 반환하거나 기본값을 설정
        }

//        Cart cart1 = new Cart();

//        Optional<ProductOption> productOption1 = productOptionRepository.findById(2l);
//
//        CartItem cartItem2 = CartItem
//                .builder()
//                .cart(cart)
//                .productOption(productOption1.get())
//                .cartItemAmount(2)
//                .build();
//
//        cartItemRepository.save(cartItem2);
    }

    @Transactional
    @Override
    public void modifyCart(List<CartItemUpdateDTO> cartItemUpdateDTOS, String userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cart.getId());

//        List<CartItemUpdateDTO> cartItemUpdates = wrapper.getCartItemUpdates();

        System.out.println("비교 준비"+ cartItemUpdateDTOS);
//        for (CartItemUpdateDTO obj1 : cartItemUpdateDTOS) {
//            System.out.println("obj1"+ obj1);
//            for (CartItem obj2 : cartItems) {
//                // Assuming MyObject has a method to identify if it's the same entity
////                assert obj1ProdOptId != null;
//                if (obj1.getCartItemId().equals(obj2.getId())) {
//                    if (obj1.getQuantity() != obj2.getCartItemAmount()) {
//                        obj2.updateCartItem(obj1.getQuantity());
//                    }
//                    break;
//                }
//            }
//        }

    }


    @Override
    public void modifybyCartId(Long cartItemId, CartItemUpdateDTO cartItemUpdateDTO) {

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            cartItem.get().updateCartItem(cartItemUpdateDTO.getQuantity());
        }
    }

    @Override
    public void deleteCart(List<Long> cartItemIds, String userId) {

        for (Long cartItemId : cartItemIds){
                cartItemRepository.deleteById(cartItemId);
        }

    }

    @Override
    public List<CartItemResponseDTO> viewCartList(String userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        List<CartItem> cartItems = cart.getCartItems();
        List<CartItemResponseDTO> cartItemResponseDTOs = new ArrayList<>();
        for(CartItem cartItem : cartItems){
            Long prodId = cartItem.getProductOption().getProduct().getProdId();
            Optional<Product> product = productRepository.findById(prodId);

            CartItemResponseDTO cartItemResponseDTO = CartItemResponseDTO.from(cartItem, product.get());
            cartItemResponseDTOs.add(cartItemResponseDTO);
        }
        System.out.println("--------cart responseDTO------" + cartItemResponseDTOs);

        return cartItemResponseDTOs;

//        Cart cart = cartRepository.findByUser_UserId(userId);
//        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cart.getId());

        // 모던 자바 1 액션 8~10버전 변화 흐름을 공부해야함
//        List<CartItemResponseDTO> cartItemResponseDTOs = cartItems.stream()
//                .map(CartItemResponseDTO::from)
//                .collect(Collectors.toList());

//        return cartItemResponseDTOS;
    }
}
