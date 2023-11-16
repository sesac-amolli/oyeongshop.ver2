package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemRequestDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.CartRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.file.ProviderNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
        if(user.get().getCart()==null) {
            Cart cart = Cart.createCart(user.get());
        }
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
    public void viewCartList(User user) {

        Cart cart = cartRepository.findByUser_UserId(user.getUserId());
        List<CartItem> cartItems = cart.getCartItems();
    }
}
