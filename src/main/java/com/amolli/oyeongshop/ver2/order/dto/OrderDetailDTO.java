package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetailDTO {

    private Long orderId;

    private List<Long> orderDetailIds = new ArrayList<>();
}
