package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class ProductOptionResponseListDTO {
    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;


    public ProductOptionResponseListDTO(Long prodOptId, String prodOptColor, String prodOptSize) {
        this.prodOptId = prodOptId;
        this.prodOptColor = prodOptColor;
        this.prodOptSize = prodOptSize;
    }

    // 이펙티브 자바 item - 정적(static) 팩토리 메서드
    public static ProductOptionResponseListDTO from (ProductOption productOption) {
        final Long prodOptId = productOption.getProdOptId();
        final String prodOptColor = productOption.getProdOptColor();
        final String prodOptSize = productOption.getProdOptSize();

        return new ProductOptionResponseListDTO(prodOptId, prodOptColor, prodOptSize);
    }
}
