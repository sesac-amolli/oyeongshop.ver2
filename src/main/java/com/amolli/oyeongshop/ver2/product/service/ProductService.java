package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;

import java.util.List;
import java.util.Set;

// 코드 재사용성과 확장성, 프레임워크 제작 등을 위해 추상 클래스나 인터페이스를 사용한다.
public interface ProductService {
    Product save (Product product); // [상품 등록] - 상품 정보를 저장
    Product findById(Long prodId); // [상품 상세 정보] - 상품 ID를 기반으로 상품을 조회
    Product removeDuplicateOptions(Product product); // [상품 상세 정보] - 상품 옵션의 중복 제거
    List<ProductResponse> findByProdCategoryJPQL(String sortValue); // [상품 목록] - 전체 상품을 정렬
    List<ProductResponse> findByProdCategoryJPQL(String prodCategory, String sortValue); // [상품 목록] - 상품을 카테고리별로 분류 및 정렬
    List<ProductResponse> findProductPaged(int page, int itemsPerPage); // [상품 관리] - 상품 목록을 페이징 하여 조회
    String UpdataSalesStatusYesNo(Long prodId); // [상품 관리] - 상품판매구분 YES, NO 토글
    int getTotalProductCount(); // [상품 관리] - 전체 상품의 수 조회(count 함수)

    void uploadDBForProduct(List<String> imageUrls, ProductDTO productDTO, Long prodId);

    List<ProductResponse> findProduct100();

    List<ProductResponse> findByNewProdJPQL();

    List<ProductResponse> findBySaleProdJPQL();

//    void uploadDB(List<String> imageUrls, ProductRegisterRequest productRegisterRequest);

}
