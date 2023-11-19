package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.board.dto.ReviewImgResponseDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class WishListResponseDTO {
    private Long wishListId;
    private String userId;
    private Long prodId;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodMainImgPath;

    public WishListResponseDTO(Long wishListId, String userId, Long prodId, String prodName,
                               Long prodOriginPrice, Long prodSalesPrice, String prodMainImgPath) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodMainImgPath = prodMainImgPath;
    }

    public static WishListResponseDTO from (Wishlist wishlist) {
        final Long wishListId = wishlist.getWishListId();
        final String userId = wishlist.getUser().getUserId();
        final Long prodId = wishlist.getProduct().getProdId();
        final String prodName = wishlist.getProduct().getProdName();
        final Long prodOriginPrice = wishlist.getProduct().getProdOriginPrice();
        final Long prodSalesPrice = wishlist.getProduct().getProdSalesPrice();
        final String prodMainImgPath = wishlist.getProduct().getProdMainImgPath();

        return new WishListResponseDTO(wishListId, userId, prodId, prodName, prodOriginPrice, prodSalesPrice, prodMainImgPath);
    }
}

