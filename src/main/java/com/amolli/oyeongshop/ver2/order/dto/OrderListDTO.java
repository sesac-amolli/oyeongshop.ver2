package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderListDTO {

    private String orderDate;
    private String orderNumber;
    private Long orderId;
    private List<OrderListDetailDTO> orderDetailList = new ArrayList<>();

    public void setOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.orderDate = orderDate.format(formatter);
    }
}
