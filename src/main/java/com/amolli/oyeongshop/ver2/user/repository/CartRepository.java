package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<User> findCartByUser(User user);

    Cart findByUserId(String userId);
}
