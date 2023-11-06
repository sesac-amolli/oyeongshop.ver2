package com.amolli.oyeongshop.ver2.board.service;

import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final ReviewRepository reviewRepository;

    public Review save(Review review) { return reviewRepository.save(review); }

}
