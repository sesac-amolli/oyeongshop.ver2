package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProdOptId(Long cartId, Long ProdOptId);
}
