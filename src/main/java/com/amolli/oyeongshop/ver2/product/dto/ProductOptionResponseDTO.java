package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductOptionResponseDTO {
    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;

    public ProductOptionResponseDTO(Long prodOptId, String prodOptColor, String prodOptSize, Long prodOptAmount) {
        this.prodOptId = prodOptId;
        this.prodOptColor = prodOptColor;
        this.prodOptSize = prodOptSize;
        this.prodOptAmount = prodOptAmount;
    }

    public static ProductOptionResponseDTO from(ProductOption productOption){
        final Long prodOptId = productOption.getProdOptId();
        final String prodOptColor = productOption.getProdOptColor();
        final String prodOptSize = productOption.getProdOptSize();
        final Long prodOptAmount = productOption.getProdOptAmount();

        return new ProductOptionResponseDTO(prodOptId, prodOptColor, prodOptSize, prodOptAmount);
    }
}
