package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
//    List<ProductOption> findAll();                                Entity    pk
//    @Query(value = "SELECT * FROM tbl_product_option WHERE prod_opt_color = :prodOptColor", nativeQuery = true)
//    List<ProductOption> productOptionSize(@Param("prodOptColor") String prodOptColor);
    @Transactional
//    @Modifying
    @Query("SELECT p.prodOptId FROM ProductOption p WHERE p.product.prodId = :prodId AND p.prodOptColor = :prodOptColor AND p.prodOptSize = :prodOptSize")
    Long findProdOptIdByProdIdAndProdOptColorAndProdOptSize(@Param("prodId") Long prodId, @Param("prodOptColor") String prodOptColor, @Param("prodOptSize") String prodOptSize);

    // 상품 옵션을 prod_opt_id가 아니라 prod_id로 찾기 위한 코드
    List<ProductOption> findByProduct_ProdId(Long prodId);

    // 상품 등록 시 옵션의 prodId가 null인데 이를 업데이트하는 코드(도저히 뭐가 문제인지 모르겠습니다)
    @Transactional
    @Modifying
    @Query(value = "UPDATE tbl_product_option SET prod_id = :prodId WHERE prod_id IS NULL", nativeQuery = true)
    void updateProdIdWhereNull(@Param("prodId") Long prodId);
}