package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDetailDTO {

    private Long orderId;

    private List<Long> orderDetailIds = new ArrayList<>();
}
