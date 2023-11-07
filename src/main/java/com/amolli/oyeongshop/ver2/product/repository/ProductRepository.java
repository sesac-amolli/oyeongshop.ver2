package com.amolli.oyeongshop.ver2.product.repository;

import com.amolli.oyeongshop.ver2.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//  JPA(Java Persistence API)를 사용하여 데이터베이스와 상호작용하는 데 사용되는 인터페이스
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAll();                           Entity    pk
}