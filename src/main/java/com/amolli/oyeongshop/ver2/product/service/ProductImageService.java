package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

import java.util.List;

public interface ProductImageService {
    void uploadDB(List<String> imageUrls, ProductDTO productDTO, Long prodId, PrincipalDetails userDetails);
    List<ProductImage> findByProduct_ProdId(Long prodId); // [상품 상세 정보] - 상품 ID를 기반으로 상품을 조회

}
