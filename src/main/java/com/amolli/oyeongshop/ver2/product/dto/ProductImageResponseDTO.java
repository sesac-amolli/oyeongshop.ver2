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
    private String prodImageUrl;

    public ProductImageResponseDTO(Long prodImageId, String prodImageUrl) {
        this.prodImageId = prodImageId;
        this.prodImageUrl = prodImageUrl;
    }

    public static ProductImageResponseDTO from(ProductImage productImage) {
        final Long prodImageId = productImage.getProdImageId();
        final String prodImageUrl = productImage.getProdServerFilePath();

        return new ProductImageResponseDTO(prodImageId, prodImageUrl);
    }
}
