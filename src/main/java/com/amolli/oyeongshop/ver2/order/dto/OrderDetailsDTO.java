package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsDTO {

    private List<OrderItemDTO> orderDetails;
}
