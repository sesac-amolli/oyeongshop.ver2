package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
@NoArgsConstructor
public class WishListDTO {

    private Long wishListId;


    public WishListDTO(Long wishListId, Product product, User user) {

        this.wishListId = wishListId;
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .wishListId(wishListId)
                .build();
    }

}
