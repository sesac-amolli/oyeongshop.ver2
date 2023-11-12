package com.amolli.oyeongshop.ver2.board.service;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;

import java.util.List;

public interface ReviewService {
    void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, Long prodId);

    List<Review> findAll();

    List<Review> findByProdId(Long prodId);

}
