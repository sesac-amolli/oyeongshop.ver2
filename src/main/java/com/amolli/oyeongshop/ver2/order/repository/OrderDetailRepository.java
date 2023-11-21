package com.amolli.oyeongshop.ver2.order.repository;

import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long> {
    @Query("SELECT o FROM OrderDetail o WHERE o.order.id = :orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT od.orderDetailId FROM OrderDetail od WHERE od.order.id = :orderId")
    List<Long> findOrderDetailIdsByOrderId(@Param("orderId") Long orderId);
}
