package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.model.User;

public interface OrderService {

    Long order(OrderDetailsDto orderDetailsDTO, OrderDeliveryDto orderDeliveryDTO, String userId);
    OrderUserDto setOrderUserDto(PrincipalDetails userDetails);

    OrderDto setPreparedOrderDto(OrderItemDto orderItemDto);
}
