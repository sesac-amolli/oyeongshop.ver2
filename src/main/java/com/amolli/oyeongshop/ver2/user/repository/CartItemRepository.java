package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCart_IdAndProductOption_ProdOptId(Long cartId, Long prodOptId);

    List<CartItem> findByCart_Id(Long cartId);
}
