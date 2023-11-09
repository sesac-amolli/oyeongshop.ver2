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
public class ReviewImgDTO {

    private String reviewServerFileName;

    public ReviewImgDTO(String reviewServerFileName) {
        this.reviewServerFileName = reviewServerFileName;
    }

    public ReviewImg toEntity() {
        return ReviewImg.builder()
                .reviewServerFileName(reviewServerFileName).build();
    }
//    @Builder
//    public ReviewImgDTO(ReviewImg reviewImg) {
//        final String reviewServerFileName = reviewImg.getReviewServerFileName();
//    }
}
