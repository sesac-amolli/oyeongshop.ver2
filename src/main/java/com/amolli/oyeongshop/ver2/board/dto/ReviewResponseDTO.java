package com.amolli.oyeongshop.ver2.board.dto;

import com.amolli.oyeongshop.ver2.board.model.Review;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@ToString
public class ReviewResponseDTO {
    private Long reviewId;
    private String userId;
    private String reviewContent;
    private Long reviewRate;
    private LocalDate reviewWriteDate;
    // ReviewImg에 대한 데이터를 담을 필드
    private List<ReviewImgResponseDTO> imgs;

    // 생성자


    public ReviewResponseDTO(Long reviewId, String userId, String reviewContent,
                             Long reviewRate, LocalDate reviewWriteDate, List<ReviewImgResponseDTO> imgs) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.reviewWriteDate = reviewWriteDate;
        this.imgs = imgs;
    }

    // 이펙티브 자바 item - 정적(static) 팩토리 메서드
    public static ReviewResponseDTO from (Review review) {
        final Long reviewId = review.getReviewId();
        final String userId = review.getUser().getUserId();
        final String reviewContent = review.getReviewContent();
        final Long reviewRate = review.getReviewRate();
        final LocalDate reviewWriteDate = review.getReviewWriteDate();
        // reviewImg 데이터 맵핑
        final List<ReviewImgResponseDTO> reviewImgResponse = review.getReviewImgs().stream().map(ReviewImgResponseDTO::from)
                .collect(Collectors.toList());

        return new ReviewResponseDTO(reviewId, userId, reviewContent, reviewRate, reviewWriteDate, reviewImgResponse);
    }
}
