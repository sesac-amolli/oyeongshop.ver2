package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
//    List<ProductOption> findAll();                                Entity    pk
    @Query("SELECT p.prodOptId FROM ProductOption p WHERE p.product.prodId = :prodId AND p.prodOptColor = :prodOptColor AND p.prodOptSize = :prodOptSize")
    Long findProdOptIdByProdIdAndProdOptColorAndProdOptSize(@Param("prodId") Long prodId, @Param("prodOptColor") String prodOptColor, @Param("prodOptSize") String prodOptSize);

    // 상품 옵션을 prod_opt_id가 아니라 prod_id로 찾기 위한 코드
    List<ProductOption> findByProduct_ProdId(Long prodId);
}