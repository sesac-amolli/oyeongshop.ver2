package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderPayDto {
    OrderDeliveryDTO orderDeliveryDTO;
    List<OrderItemDTO> orderItemDTOs;
    OrderPriceDTO orderPriceDTO;
}
