package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ProductImageResponseDTO {
    private Long prodImageId;
    private String prodServerFilePath;

    public ProductImageResponseDTO(Long prodImageId, String prodServerFilePath) {
        this.prodImageId = prodImageId;
        this.prodServerFilePath = prodServerFilePath;
    }

    public static ProductImageResponseDTO from(ProductImage productImage) {
        final Long prodImageId = productImage.getProdImageId();
        final String prodServerFilePath = productImage.getProdServerFilePath();

        return new ProductImageResponseDTO(prodImageId, prodServerFilePath);
    }
}
