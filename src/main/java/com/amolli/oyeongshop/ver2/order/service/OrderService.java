package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderDetailsDTO;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;

import java.util.List;

public interface OrderService {

    public Long order(OrderDetailsDTO orderDetailsDTO, OrderDeliveryDTO orderDeliveryDTO, String userId);
    Order save(Order order);
}
