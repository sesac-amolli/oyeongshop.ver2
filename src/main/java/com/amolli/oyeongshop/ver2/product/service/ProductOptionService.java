package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductOptionDTO;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;

import java.util.List;

public interface ProductOptionService {
    void saveProductOption (ProductOptionDTO productOptionDTO); // [상품 등록] 상품 옵션 정보를 저장

    List<ProductOption> findByProduct_ProdId(Long prodId); // [상품 상세 정보] 상품 ID를 기반으로 상품을 조회
}
