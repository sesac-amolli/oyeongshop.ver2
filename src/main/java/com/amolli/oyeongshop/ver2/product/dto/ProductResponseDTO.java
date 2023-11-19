package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProductResponseDTO {
    private Long prodId;
    private List<ProductImageResponseDTO> productImages;

    public ProductResponseDTO(Long prodId, List<ProductImageResponseDTO> productImages) {
        this.prodId = prodId;
        this.productImages = productImages;
    }

    public static ProductResponseDTO from(Product product) {
        final Long prodId = product.getProdId();
        final List<ProductImageResponseDTO> prodImages = product.getProductImages().stream().map(ProductImageResponseDTO::from)
                .collect(Collectors.toList());

        return new ProductResponseDTO(prodId, prodImages);
    }
}
