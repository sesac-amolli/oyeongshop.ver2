package com.amolli.oyeongshop.ver2.board.service;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.board.repository.ReviewRepository;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    // uploadS3에서 받아온 url리스트, ReviewDTO
    public void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, Long prodId) {

        // CrudRepository에서 findById는 return 타입이 Optional이다.
        // 아래를 productRepository.findById(prodId).orElse()Repository 한줄로도 가능
        // prodId 받아서 findById 해서 Product 객체에 넣어줌
        Optional<Product> optionalProduct = productRepository.findById(prodId);
        // optionalProduct가 존재하지 않으면 RuntimeException 던지기
        if(!optionalProduct.isPresent()) {
            throw new RuntimeException("Category id: " + prodId + " can not found!!");
        }

        // ReviewDto를 Entity로 바꿔주고 review엔티티에 넣어줌.
        Review review = reviewDTO.toEntity();
        // ProductId도 같이 insert 해야돼서 optionalProduct를 다 get해서 review에 set해줌
        review.setProduct(optionalProduct.get());

        for(String url : imageUrls) {
            ReviewImg reviewImg = new ReviewImg();
            // reviewImg에 url 하나씩 serverfilename에 set
            reviewImg.setReviewServerFileName(url);

            review.addReviewImg(reviewImg);

        }
        // review db에 insert
        reviewRepository.save(review);
    }
}
