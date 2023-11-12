package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItemDTO {

    private Long orderDetailId;

    private Long ordeDetailAmount;

    private Long orderDetailPrice;

    private Order order;

    private ProductOption productOption;
}
