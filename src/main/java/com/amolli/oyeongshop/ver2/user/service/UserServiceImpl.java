package com.amolli.oyeongshop.ver2.user.service;


import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.WishListDTO;
import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.Point;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishlistRepository wishlistRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    @Transactional
    public void signUp(UserDTO userDto){
        User user = userDto.toEntity();
        System.out.println(user);
        Point point = new Point("적립", "회원가입 축하 적립금", 1000L);
        user.givePoint(1000L, point);
        user.setCart(new Cart(user));
        String rawPwd = user.getUserPwd();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.encPwd(encPwd);
        userRepository.save(user);
    }

    @Override
    public void uploadWish(PrincipalDetails userDetails, Long prodId, WishListDTO wishListDTO) {
        Optional<User> optionalUser = userRepository.findById(userDetails.getUser().getUserId());
        Optional<Product> optionalProduct = productRepository.findById(prodId);

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Prod id: " + prodId + " can not found!!");
        }

        Wishlist wishlist = wishListDTO.toEntity();
        wishlist.setUser(optionalUser.get());
        wishlist.setProduct(optionalProduct.get());

        wishlistRepository.save(wishlist);

    }
}
