package com.amolli.oyeongshop.ver2.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tblReview")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    private Long reviewId;

    // 추후 외래키 연결(user테이블의 userId와)
    private String userId;

    private String reviewContent;

    private LocalDate reviewWriteDate;

    // 상품id가 외래키라서 선언
    private Product product;

    private Long reviewRate;

    @OneToMany(mappedBy = "tblReview")
    private List<ReviewImg> reviewImgs = new ArrayList<ReviewImg>();

}
