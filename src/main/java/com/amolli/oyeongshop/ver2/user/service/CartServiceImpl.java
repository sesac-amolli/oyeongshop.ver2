package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.CartRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public void modifyCart(List<CartItemRequestDTO> cartItemRequestDTOS, String userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cart.getId());

        for (CartItemRequestDTO obj1 : cartItemRequestDTOS) {
            Long obj1ProdOptId = productOptionRepository.findProdOptIdByProdIdAndProdOptColorAndProdOptSize(obj1.getProdId(), obj1.getColor(), obj1.getSize());
            for (CartItem obj2 : cartItems) {
                // Assuming MyObject has a method to identify if it's the same entity
//                assert obj1ProdOptId != null;
                if (obj1ProdOptId.equals(obj2.getProductOption().getProdOptId())) {
                    if (obj1.getQuantity() != obj2.getCartItemAmount()) {
                        obj2.setCartItemAmount(obj1.getQuantity());
                    }
                    break;
                }
            }
        }

    }

    @Override
    public Cart viewCartList(String userId) {

        return cartRepository.findByUser_UserId(userId);

//        Cart cart = cartRepository.findByUser_UserId(userId);
//        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cart.getId());

        // 모던 자바 1 액션 8~10버전 변화 흐름을 공부해야함
//        List<CartItemResponseDTO> cartItemResponseDTOS = cartItems.stream()
//                .map(CartItemResponseDTO::from)
//                .collect(Collectors.toList());

//        return cartItemResponseDTOS;
    }
}
