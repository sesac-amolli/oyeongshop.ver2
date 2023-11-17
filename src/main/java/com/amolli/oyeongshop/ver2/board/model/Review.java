package com.amolli.oyeongshop.ver2.board.model;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@ToString(exclude = "product")
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_review")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String reviewContent;

    private Long reviewRate;

    @CreationTimestamp
    private LocalDate reviewWriteDate;

    // 유저id가 외래키라서 연결
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    // 상품id가 외래키라서 선언
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod_id")
    private Product product;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewImg> reviewImgs = new ArrayList<>();


    public void addReviewImg(ReviewImg reviewImg) {
        reviewImgs.add(reviewImg);
        reviewImg.setReview(this);
    }
}
