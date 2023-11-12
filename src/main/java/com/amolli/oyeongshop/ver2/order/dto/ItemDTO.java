package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
public class ItemDTO {

    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;
    private Long prodId;

}
