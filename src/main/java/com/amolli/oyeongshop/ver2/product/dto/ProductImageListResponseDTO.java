package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProductImageListResponseDTO {
    private Long prodId;
    private List<ProductImageResponseDTO> productImages;

    public ProductImageListResponseDTO(Long prodId, List<ProductImageResponseDTO> productImages) {
        this.prodId = prodId;
        this.productImages = productImages;
    }

    public static ProductImageListResponseDTO from(Product product) {
        final Long prodId = product.getProdId();
        final List<ProductImageResponseDTO> prodImages = product.getProductImages().stream().map(ProductImageResponseDTO::from)
                .collect(Collectors.toList());

        return new ProductImageListResponseDTO(prodId, prodImages);
    }
}
