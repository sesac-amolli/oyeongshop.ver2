package com.amolli.oyeongshop.ver2.user.service;


import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.PointDto;
import com.amolli.oyeongshop.ver2.user.dto.WishListDTO;
import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.dto.WishListResponseDTO;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.Point;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import com.amolli.oyeongshop.ver2.user.repository.PointRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishListRepository wishlistRepository;
    private final PointRepository pointRepository;

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
    public boolean checkId(String id) {
        //id가 존재할 경우 true
        boolean result = userRepository.existsByUserId(id);
        return !result;
    }
    @Override
    public List<PointDto> myPoint(String userId){
        List<Point> points = pointRepository.findByUser_UserId(userId);
        List<PointDto> PointDtos = points.stream().map(PointDto::from).collect(Collectors.toList());
        return PointDtos;
    }

    // wishlist insert
    @Override
    public Long uploadWish(PrincipalDetails userDetails, Long prodId, WishListDTO wishListDTO) {
        Optional<User> optionalUser = userRepository.findById(userDetails.getUser().getUserId());
        Optional<Product> optionalProduct = productRepository.findById(prodId);

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Prod id: " + prodId + " can not found!!");
        }

        Wishlist wishlist = wishListDTO.toEntity();
        wishlist.setUser(optionalUser.get());
        wishlist.setProduct(optionalProduct.get());

        Wishlist wishlist1 = wishlistRepository.save(wishlist);

        return wishlist1.getWishListId();
    }

    // wishlistId 찾는 메서드
    @Override
    public Long findWishList(Long prodId, PrincipalDetails details) {
        String userId = details.getUser().getUserId();
        System.out.println("dddd"+userId+"prodId::"+prodId);
        Optional<Wishlist> wishlist = wishlistRepository.findByProductProdIdAndUserUserId(prodId, userId);
        Long wishListId = 0L;
        if (wishlist.isPresent()) {
            wishListId = wishlist.get().getWishListId();
        }
        return wishListId;
    }

    // wishlist delete
    @Override
    public void deleteWishList(Long wishListId) {
        wishlistRepository.deleteById(wishListId);
    }

    @Override
    public List<Wishlist> findMyWishList(PrincipalDetails details) {
        String userId = details.getUser().getUserId();
        return wishlistRepository.findByUser_UserId(userId);
    }

}
