package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class OrderDetailsResponseDTO {

    private List<OrderDetailResponseDTO> orderDetailItems = new ArrayList<>();
}
