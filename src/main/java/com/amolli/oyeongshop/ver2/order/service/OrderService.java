package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<String, String> order(List<OrderItemDTO> orderItemsDTO, OrderDeliveryDTO orderDeliveryDTO, OrderPriceDTO orderPriceDTO, String userId);
    OrderUserDTO setOrderUserDto(PrincipalDetails userDetails);

    OrdersDTO setOrdersDTO(OrderItemDTO orderItemDto);

    OrdersDTO setOrdersDTO(List<Long> selectedItems);

    OrderResponseDTO setOrderResponseDTO(Long orderId);

    OrderDetailsResponseDTO setOrderDetailResponseDTO(List<Long> orderDetailIDs);

    OrderDetailDTO setOrderDetailDTO(Long orderId);

    OrderDetailsResponseDTO setOrderDetailResponseDTO(Long orderId);

    List<OrderListDTO> setOrderListDTOList(String userId);

    void payment(PaymentDto paymentDto);

//    Page<OrderListDTO> setOrderListDTOListPage(String userId, int pageNumber);

}
