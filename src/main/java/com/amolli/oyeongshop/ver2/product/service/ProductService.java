package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;

import java.util.List;

// 코드 재사용성과 확장성, 프레임워크 제작등을 위해 추상 클래스나 인터페이스를 사용한다.
public interface ProductService {
    Product save (Product product); // 상품 정보를 저장
    Product findById(Long prodId); // 상품 ID를 기반으로 상품을 조회
    List<ProductResponse> findProductBySalesDist(String prodSalesDist); // 판매 여부에 따라 상품을 조회
    Product removeDuplicateOptions(Product product); // 상품 옵션 중복을 제거
    List<ProductResponse> findByProdCategoryJPQL(String prodCategory, String value); // 특정 카테고리에 속하는 상품을 조회(카테고리별 조회)
    List<ProductResponse> findByProdCategoryJPQL(String prodCategory); // 특정 카테고리에 속하는 상품을 조회(카테고리별 조회)
    List<ProductResponse> findProductPaged(int page, int itemsPerPage); // 페이지별로 상품을 조회
    int getTotalProductCount(); // 전체 상품의 수를 조회
    String UpdataSalesStatusYesNo(Long prodId);

}
