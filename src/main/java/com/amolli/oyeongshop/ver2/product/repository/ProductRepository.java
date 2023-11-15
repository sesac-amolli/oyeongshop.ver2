package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAll();                           Entity    pk
    List<Product> findByProdSalesDist(String prodSalesDist);

    // 상품을 카테고리별로 조회, 상품판매구분이 YES 인 경우의 리스트만 보여줌
    @Query("SELECT p FROM Product p WHERE p.prodSalesDist = 'YES' and :prodCategory = p.prodCategory")
//    COALESCE(:prodCategory, p.prodCategory) = p.prodCategory and
    List<Product> findByProdCategoryJPQL(@Param("prodCategory") String prodCategory, Sort sort);

    // 상품판매여부가 YES인 경우의 LIST를 출력
    @Query("SELECT p FROM Product p WHERE p.prodSalesDist = 'YES'")
    List<Product> findByProdJPQL(Sort sort);

    // 상품판매구분 업데이트를 위한 쿼리
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.prodSalesDist = ?2 WHERE p.prodId = ?1")
    void updateSalesDist(Long prodId, String prodSalesDist);
}