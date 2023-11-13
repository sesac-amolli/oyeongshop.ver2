package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDto;
import com.amolli.oyeongshop.ver2.order.dto.OrderDetailsDto;
import com.amolli.oyeongshop.ver2.order.model.Order;

public interface OrderService {

    public Long order(OrderDetailsDto orderDetailsDTO, OrderDeliveryDto orderDeliveryDTO, String userId);
    Order save(Order order);
}
