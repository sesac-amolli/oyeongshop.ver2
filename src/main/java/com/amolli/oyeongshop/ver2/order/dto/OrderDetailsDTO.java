package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class OrderDetailsDTO {

    private List<OrderItemDTO> orderDetails;
}
