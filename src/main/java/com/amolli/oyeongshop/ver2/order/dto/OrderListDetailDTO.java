package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderListDetailDTO {

    private Long prodId;
    private String prodMainImgPath;
    private String prodName;
    private String prodOptSize;
    private String prodOptColor;
    private Long orderDetailAmount;
    private Long orderDetailSalesPrice;
    private OrderStatus orderStatus;
}
