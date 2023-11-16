package com.amolli.oyeongshop.ver2.user.dto;

import lombok.*;

@Setter
@Getter
@Data
public class CartItemResponseDTO {

    private Long prodId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;
    private Long prodSalesPrice;
    private Long prodOptId;
    private String prodName;

    public CartItemResponseDTO(Long prodId, String prodOptColor, String prodOptSize, Long prodOptAmount, Long prodSalesPrice, Long prodOptId, String prodName) {
        this.prodId = prodId;
        this.prodOptColor = prodOptColor;
        this.prodOptSize = prodOptSize;
        this.prodOptAmount = prodOptAmount;
        this.prodSalesPrice = prodSalesPrice;
        this.prodOptId = prodOptId;
        this.prodName = prodName;
    }
}
