package com.amolli.oyeongshop.ver2.order.repository;

import com.amolli.oyeongshop.ver2.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //Order findByUserId(String userId);
}
