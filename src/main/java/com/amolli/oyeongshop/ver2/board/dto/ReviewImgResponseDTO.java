package com.amolli.oyeongshop.ver2.board.dto;


import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ReviewImgResponseDTO {
    private Long reviewImgId;
    private String reviewImgUrl;

    public ReviewImgResponseDTO(Long reviewImgId, String reviewImgUrl) {
        this.reviewImgId = reviewImgId;
        this.reviewImgUrl = reviewImgUrl;
    }


    //    // 미사용
//    public ReviewImg toEntity() {
//        return ReviewImg.builder()
//                .reviewServerFileName(reviewServerFileName).build();
//    }

    public static ReviewImgResponseDTO from(ReviewImg reviewImg) {
        final Long reviewImgId = reviewImg.getReviewImageId();
        final String reviewImgUrl = reviewImg.getReviewServerFileName();

        return new ReviewImgResponseDTO(reviewImgId, reviewImgUrl);
    }
}
