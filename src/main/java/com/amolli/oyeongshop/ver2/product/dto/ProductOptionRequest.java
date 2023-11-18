package com.amolli.oyeongshop.ver2.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
public class ProductOptionRequest {
    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;

    public ProductOptionRequest(Long prodOptId, String prodOptColor, String prodOptSize, Long prodOptAmount) {
        this.prodOptId = prodOptId;
        this.prodOptColor = prodOptColor;
        this.prodOptSize = prodOptSize;
        this.prodOptAmount = prodOptAmount;
    }
}
