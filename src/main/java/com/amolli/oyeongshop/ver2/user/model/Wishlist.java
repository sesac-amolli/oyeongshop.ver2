package com.amolli.oyeongshop.ver2.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tblWishlist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist extends BaseEntity{

    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "prod_id")
    private Product product;
}
