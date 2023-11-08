package com.amolli.oyeongshop.ver2.user.model;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    private String userId;

    @Column(nullable = false)
    @ColumnDefault("'USER'")
    private String userGrade;

    private String userName;

    private String userPwd;

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

    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserAddr> userAddrs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Point> points = new ArrayList<>();

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
