package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser_UserId(String userId);

    Optional<Wishlist> findByProductProdIdAndUserUserId(Long prodId, String userId);


}
