package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAll();                           Entity    pk

    // [상품 목록] 상품 목록을 조회
    List<Product> findByProdId(Long prodId);

    // [상품 목록] 상품을 카테고리별로 조회, 상품판매구분이 YES 인 경우의 리스트만 보여줌
    @Query("SELECT p FROM Product p WHERE p.prodSalesDist = 'YES' and :prodCategory = p.prodCategory")
    List<Product> findByProdCategoryJPQL(@Param("prodCategory") String prodCategory, Sort sort);

    // [상품 목록] best100 상품 조회
    @Query("SELECT p.prodId, p.prodName, p.prodCategory, p.prodOriginPrice, p.prodSalesPrice, p.prodMainImgPath, MAX(o.prodOptAmount) " +
            "FROM Product p JOIN p.productOptions o " +
            "WHERE p.prodSalesDist = 'YES' GROUP BY p.prodId ORDER BY MAX(o.prodOptAmount) DESC")
    List<Object[]> findByTopProdJPQL(Pageable pageable);

    // [상품 목록] 신상품 18개 list 출력
    @Query("SELECT p FROM Product p WHERE p.prodSalesDist = 'YES'")
    List<Product> findByProdJPQL(Pageable pageable);

    // [상품 목록] sales 상품 조회
    @Query("SELECT p FROM Product p WHERE p.prodOriginPrice <> p.prodSalesPrice")
    List<Product> findSaleProducts(Sort sort);

    // [상품 목록] 상품명 또는 상품 코드로 상품 목록 검색
    @Query("SELECT p FROM Product p WHERE p.prodName LIKE %:prodName% OR p.prodCode LIKE %:prodName%")
    List<Product> findByProdNameOrCodeJPQL(@Param("prodName") String search);

    // [상품 관리] 상품판매여부가 YES인 경우의 LIST를 출력
    @Query("SELECT p FROM Product p WHERE p.prodSalesDist = 'YES'")
    List<Product> findByProdJPQL(Sort sort);

    // [상품 관리] 상품판매구분 업데이트를 위한 쿼리
    @Modifying
    @Query("UPDATE Product p SET p.prodSalesDist = ?2 WHERE p.prodId = ?1")
    void updateSalesDist(Long prodId, String prodSalesDist);

    // [상품 등록] 이미지 등록 시 첫 사진을 메인 사진으로 사용하기 위한 명령어
    @Modifying
    @Query(value = "UPDATE Product p SET p.prodMainImgPath = ?1 WHERE p.prodId = ?2")
    void updateMainImagePath(String prodMainImgPath, Long prodId);
}