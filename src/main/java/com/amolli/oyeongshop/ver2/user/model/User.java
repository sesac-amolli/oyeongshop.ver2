package com.amolli.oyeongshop.ver2.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblUser")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    private String userId;

    private String userGrade;

    private String userName;

    private String userPwd;

    private String userPhone;

    private Date userRegdate;

    private Long userPoint;

    private String userStatus;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserAddr> userAddrs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Point> points = new ArrayList<>();
}
