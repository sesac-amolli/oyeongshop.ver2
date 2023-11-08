package com.amolli.oyeongshop.ver2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tbl_user")
@DynamicInsert
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(nullable = false)
    @ColumnDefault("'USER'")
    private String userGrade;

    private String userName;
  
    // 23.11.06 스프링 시큐리티 확인용 userPwd > password 로 정정
    // @JsonIgnore
    // @Column(name = "password", length = 100)
    private String userPwd;

    // 23.11.06 스프링 시큐리티 확인용 userEmail > email 로 정정
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;

    private String userPhone;

    private String userEmail;

    @Column(nullable = false)
    @ColumnDefault("'OY'")
    private String userSnsDist;

    @CreationTimestamp
    private LocalDate userRegdate;


    private Long userPoint;

    @Column(nullable = false)
    @ColumnDefault("'YES'") // default
    private String userStatus;


//    @Builder
//    public User( String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserAddr> userAddrs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Point> points = new ArrayList<>();

   @ManyToMany
   @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "authority_id")})
    private Set<Authority> authorities;


    public void encPwd(String userPwd){
        this.userPwd = userPwd;
    }

    public void givePoint(Long point){
        userPoint=point;
    }

    public void giveGrade(String grade){
        userGrade=grade;
    }

    @Builder
    public User(String userId, String userGrade, String userName, String userPwd, String userPhone, String userEmail,
                String userSnsDist, LocalDate userRegdate, Long userPoint, String userStatus, List<Cart> carts,
                List<UserAddr> userAddrs, List<Wishlist> wishlists, List<Point> points) {
        this.userId = userId;
        this.userGrade = userGrade;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userSnsDist = userSnsDist;
        this.userRegdate = userRegdate;
        this.userPoint = userPoint;
        this.userStatus = userStatus;
        this.carts = carts;
        this.userAddrs = userAddrs;
        this.wishlists = wishlists;
        this.points = points;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userGrade='" + userGrade + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userRegdate=" + userRegdate +
                ", userPoint=" + userPoint +
                ", userStatus='" + userStatus + '\'' +
                ", carts=" + carts +
                ", userAddrs=" + userAddrs +
                ", wishlists=" + wishlists +
                ", points=" + points +
                '}';
    }

}
