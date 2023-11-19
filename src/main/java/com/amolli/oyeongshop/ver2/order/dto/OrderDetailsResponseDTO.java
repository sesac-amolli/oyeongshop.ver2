package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsResponseDTO {

    private List<OrderDetailResponseDTO> orderDetailItems;
}
