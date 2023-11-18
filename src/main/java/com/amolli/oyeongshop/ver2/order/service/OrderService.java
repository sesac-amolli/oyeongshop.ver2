package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

public interface OrderService {

    Long order(OrderItemsDto orderItemsDTO, OrderDeliveryDto orderDeliveryDTO, OrderPriceDTO orderPriceDTO, String userId);
    OrderUserDto setOrderUserDto(PrincipalDetails userDetails);

    OrderDto setPreparedOrderDto(OrderItemDto orderItemDto);
}
