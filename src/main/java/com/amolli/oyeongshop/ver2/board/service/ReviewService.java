package com.amolli.oyeongshop.ver2.board.service;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
//    void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, Long prodId);
    void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, Long prodId, PrincipalDetails userDetails);

    List<Review> findAll();

    List<Review> findByProdId(Long prodId);

    List<Review> findByUserId(String userId);

    void deleteMyReview(Long reviewId);

}
