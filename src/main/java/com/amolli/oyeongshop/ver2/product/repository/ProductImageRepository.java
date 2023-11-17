package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{
    public ProductImage findOneByFileName(String filename);
}