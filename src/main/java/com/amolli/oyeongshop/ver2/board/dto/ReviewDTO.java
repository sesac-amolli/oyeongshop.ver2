package com.amolli.oyeongshop.ver2.board.dto;

import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ReviewDTO {

    private Long reviewId;
    private String userId;
    private String prodName;
    private String reviewContent;
    private Long reviewRate;
    private LocalDate reviewWriteDate;

    public ReviewDTO(Long reviewId, String userId, String prodName, String reviewContent,
                     Long reviewRate, LocalDate reviewWriteDate) {
        super();
        this.reviewId = reviewId;
        this.userId = userId;
        this.prodName = prodName;
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.reviewWriteDate = reviewWriteDate;
    }

    public Review toEntity() {
        return Review.builder()
                .reviewId(reviewId)
                .userId(userId)
                .reviewContent(reviewContent)
                .reviewRate(reviewRate)
                .reviewWriteDate(reviewWriteDate).build();
    }

//    @Builder
//    public ReviewDTO(Review review) {
//        final Long reviewId = review.getReviewId();
//        final String userId = review.getUserId();
//        final String prodName = review.getProduct().getProdName();
//        final String reviewContent = review.getReviewContent();
//        final Long reviewRate = review.getReviewRate();
//        final LocalDate reviewWriteDate = review.getReviewWriteDate();
//
//    }

}
