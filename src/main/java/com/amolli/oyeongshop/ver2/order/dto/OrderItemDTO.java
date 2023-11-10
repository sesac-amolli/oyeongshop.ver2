package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Data;

@Data
public class OrderItemDTO {

    private Long orderDetailId;

    private Long ordeDetailAmount;

    private Long orderDetailPrice;

    private Order order;

    private ProductOption productOption;
}
