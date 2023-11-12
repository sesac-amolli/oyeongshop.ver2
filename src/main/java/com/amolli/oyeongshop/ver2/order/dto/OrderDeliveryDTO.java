package com.amolli.oyeongshop.ver2.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class OrderDeliveryDTO {

    private String orderAttnName;

    private String orderAttnPhone;

    private String orderAttnPostcode;

    private String orderAttnAddr1;

    private String orderAttnAddr2;

    private String orderAttnDetail;

    private String orderAttnRequest;

    @Builder
    public OrderDeliveryDTO(String orderAttnName, String orderAttnPhone, String orderAttnPostcode, String orderAttnAddr1, String orderAttnAddr2, String orderAttnDetail, String orderAttnRequest) {
        this.orderAttnName = orderAttnName;
        this.orderAttnPhone = orderAttnPhone;
        this.orderAttnPostcode = orderAttnPostcode;
        this.orderAttnAddr1 = orderAttnAddr1;
        this.orderAttnAddr2 = orderAttnAddr2;
        this.orderAttnDetail = orderAttnDetail;
        this.orderAttnRequest = orderAttnRequest;
    }
}
