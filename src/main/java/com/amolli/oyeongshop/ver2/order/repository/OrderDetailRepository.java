package com.amolli.oyeongshop.ver2.order.repository;

import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long> {
}
