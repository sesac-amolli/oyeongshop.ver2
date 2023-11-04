package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}