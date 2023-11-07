package com.amolli.oyeongshop.ver2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id", length = 50)
    private String userId;

    // 23.11.06 스프링 시큐리티 확인용 userPwd > password 로 정정
    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    private String userGrade;

    private String userName;

    // 23.11.06 스프링 시큐리티 확인용 userEmail > email 로 정정
    @Column(name = "email", nullable = false, unique = true)
    private String email;


    private String userPhone;

//    @CreationTimestamp
//    private LocalDate userRegdate;

    private Long userPoint;

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

}
