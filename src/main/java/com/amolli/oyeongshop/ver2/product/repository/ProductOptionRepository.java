package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
//    List<ProductOption> findAll();                                Entity    pk
//    @Query(value = "SELECT * FROM tbl_product_option WHERE prod_opt_color = :prodOptColor", nativeQuery = true)
//    List<ProductOption> productOptionSize(@Param("prodOptColor") String prodOptColor);
}