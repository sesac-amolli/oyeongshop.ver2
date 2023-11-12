package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.dto.CartDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.CartRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;

    @Override
    public void uploadDB(Cart cart, CartDTO cartDTO, Long prodOptId) {
        Optional<ProductOption> optionalProductOption = productOptionRepository.findById(prodOptId);

        if(!optionalProductOption.isPresent()) {
            throw new RuntimeException("Category id: " + prodOptId + " can not found!");
        }
        CartItem cartItem = cartDTO.toEntity();

        cartItem.setProductOption(optionalProductOption.get());

        cart.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void create(CartCreateRequestDTO req, User user) {
//        Product product = productRepository.findById(req.getProdOptId());

//        if(product.getP) DB 수량 체크 후 넣기

        if (cartRepository.findCartByUser(user).isEmpty()){
            Cart cart = new Cart(user);
            cartRepository.save(cart);
        }

        ProductOption productOption = cartItemRepository.findById(req.getProdOptId()).get().getProductOption();
        Cart cart = cartRepository.findCartByUser(user).get().getCart();

        CartItem cartItem = new CartItem(productOption, cart, req.getCartItemDate(), req.getQuantity());

        cartItemRepository.save(cartItem);
    }
}
