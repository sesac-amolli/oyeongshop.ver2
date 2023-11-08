package com.amolli.oyeongshop.ver2.board.service;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, Long prodId);
}
