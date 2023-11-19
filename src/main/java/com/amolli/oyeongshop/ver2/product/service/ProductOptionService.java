package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductOptionResponse;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOptionService {
    ProductOption saveProductOption (ProductOption productOption); // [상품 등록] - 상품 옵션 정보를 저장
//    List<ProductOption> getProductOptions();
    List<ProductOption> findByProduct_ProdId(Long prodId); // [상품 상세 정보] - 상품 ID를 기반으로 상품을 조회

//    String UpdataProdId(Long prodId);
    void updateProdIdWhereNull(@Param("prodId") Long prodId);
}
