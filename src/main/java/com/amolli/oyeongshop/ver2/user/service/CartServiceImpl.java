package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
//import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartItemDTO;
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
    public void addCart(Long prodOptId, int amount, UserDetails userDetails) {


        Cart cart = cartRepository.findByUser_UserId(userDetails.getUsername());
        ProductOption productOption = productOptionRepository.findById(prodOptId);
//        User user = cartRepository.findByUser_UserId(userId);
//        Cart cart = cartItemRepository.findByCart_IdAndProductOption_ProdOptId(user.getCart(), prodOptId);
//        ProductOption productOption = cartItemRepository.findByProductOption_ProdOptId(prodOptId);


        CartItem cartItem = CartItem
                .builder()
                .cart(cart)
                .productOption(productOption)
                .cartItemAmount(amount)
                .build();

        cartItemRepository.save(cartItem);

//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setProductOption(productOption);
//        cartItem.setCartItemAmount(amount);

    }
}
