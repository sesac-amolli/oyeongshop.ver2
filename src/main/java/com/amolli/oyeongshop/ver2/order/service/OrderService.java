package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

import java.util.List;

public interface OrderService {

    Long order(OrderItemsDTO orderItemsDTO, OrderDeliveryDTO orderDeliveryDTO, OrderPriceDTO orderPriceDTO, String userId);
    OrderUserDTO setOrderUserDto(PrincipalDetails userDetails);

    OrdersDTO setOrdersDTO(OrderItemDTO orderItemDto);

    OrdersDTO setOrdersDTO(List<Long> selectedItems);

//    OrderDTO setPreparedOrderDto(OrderItemDTO orderItemDto);
}
