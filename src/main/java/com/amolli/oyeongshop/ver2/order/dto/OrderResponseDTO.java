package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.order.model.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class OrderResponseDTO {

    private String orderNumber;
    private String orderDate;
    private String userName;
    private OrderStatus orderStatus;
    private Long orderOriginPrice;
    private Long orderDiscount;
    private Long orderTotalPayment; //포인트 미적용
    private String orderAttnName;
    private String orderAttnPhone;
    private String orderAttnEmail;
    private String orderAttnPostcode;
    private String orderAttnAddr1;
    private String orderAttnAddr2;
    private String orderAttnDetail;
    private String orderAttnRequest;

    public void setOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.orderDate = orderDate.format(formatter);
    }
}
