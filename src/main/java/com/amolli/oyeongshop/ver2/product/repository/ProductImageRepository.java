package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{

    // 상품 옵션을 prod_opt_id가 아니라 prod_id로 찾기 위한 코드
    List<ProductImage> findByProduct_ProdId(Long prodId);
}