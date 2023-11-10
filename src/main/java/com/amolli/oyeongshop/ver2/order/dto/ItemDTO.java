package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ItemDTO {

    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;
    private Product product;

}
