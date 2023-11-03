package com.amolli.oyeongshop.ver2.board.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tblReviewImg")
public class ReviewImg {

    @Id
    @GeneratedValue
    private Long reviewImageId;

    private String reviewUserFileName;

    private String reviewServerFileName;

    @JoinTable("")
    // 리뷰게시판 리뷰id를 외래키
    private Review review;

}
