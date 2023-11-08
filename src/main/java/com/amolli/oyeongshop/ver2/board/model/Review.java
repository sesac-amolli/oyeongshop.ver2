package com.amolli.oyeongshop.ver2.board.model;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Builder
@Entity
@Table(name = "tbl_review")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    // 추후 외래키 연결(user테이블의 userId와)
    private String userId;

    private String reviewContent;

    private Long reviewRate;

    @CreationTimestamp
    private LocalDate reviewWriteDate;

    // 상품id가 외래키라서 선언
    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImg> reviewImgs = new ArrayList<>();

//    @Builder
//    public Review(ReviewDTO reviewDTO) {
//        final String userId = reviewDTO.getUserId();
//        final String prodName = reviewDTO.getProdName();
//        final String reviewContent = reviewDTO.getReviewContent();
//        final Long reviewRate = reviewDTO.getReviewRate();
//        final LocalDate reviewWriteDate = reviewDTO.getReviewWriteDate();
//
//    }
    public void addReviewImg(ReviewImg reviewImg) {
        reviewImgs.add(reviewImg);
        reviewImg.setReview(this);
    }

}
