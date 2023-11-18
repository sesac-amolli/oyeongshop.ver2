package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDto {

    private List<OrderDto> orderItems;

}