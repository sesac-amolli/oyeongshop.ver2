//package com.amolli.oyeongshop.ver2.user.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import com.amolli.oyeongshop.ver2.product.model.ProductOption;
//import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
//import com.amolli.oyeongshop.ver2.user.dto.CartItemDTO;
//import com.amolli.oyeongshop.ver2.user.model.CartItem;
//import com.amolli.oyeongshop.ver2.user.model.User;
//import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
//import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
//import com.amolli.oyeongshop.ver2.user.service.CartService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//
//@SpringBootTest
//@Transactional
//@TestPropertySource(locations = "classpath:application-test.yml")
//public class CartControllerTest {
//
//    @Autowired
//    ProductOptionRepository productOptionRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    CartItemRepository cartItemRepository;
//
//    public ProductOption saveProductOption(){
//        ProductOption productOption = new ProductOption();
//        productOption.setProdOptId(Long.valueOf("123"));
//        productOption.setProdOptColor("red");
//        productOption.setProdOptAmount(Long.valueOf("11"));
//        productOption.setProdOptSize("large");
//        return productOptionRepository.save(productOption);
//    }
//
//    public User saveUser(){
//        User user = new User();
//        user.setUserEmail("test@test.com");
//        return userRepository.save(user);
//    }
//
//    @Test
//    @DisplayName("장바구니 담기 테스트")
//    public void addCart(){
//        ProductOption productOption = saveProductOption();
//        User user = saveUser();
//
//        CartItemDTO cartItemDTO = new CartItemDTO();
//        cartItemDTO.setCartItemAmount(5L);
//        cartItemDTO.setCartItemId(productOption.getProdOptId());
//
//        Long cartItemId = cartService.addCart(cartItemDTO,
//                user.getUserEmail());
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        assertEquals(productOption.getProdOptId(), cartItem.getProductOption().getProdOptId());
//        assertEquals(cartItemDTO.getCartItemAmount(), cartItem.getCartItemAmount());
//
//    }
//}