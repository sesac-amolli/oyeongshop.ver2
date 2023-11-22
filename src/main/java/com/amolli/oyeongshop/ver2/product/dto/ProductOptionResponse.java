package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductOptionResponse {
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    private String prodDesc;
    private LocalDateTime prodRegDate;
    private String prodMainImgPath;
    private String prodSalesDist;
    private List<ProductOptionResponseDTO> productOptionResponseDTO;

    public ProductOptionResponse(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, LocalDateTime prodRegDate, String prodCategory,
                                 String prodMainImgPath, String prodSalesDist, List<ProductOptionResponseDTO> productOptionResponseDTO) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodRegDate = prodRegDate;
        this.prodCategory = prodCategory;
        this.prodMainImgPath = prodMainImgPath;
        this.prodSalesDist = prodSalesDist;
        this.productOptionResponseDTO = productOptionResponseDTO;
    }

    public static ProductOptionResponse from(Product product) {
        final Long prodId = product.getProdId();
        final String prodCode = product.getProdCode();
        final String prodName = product.getProdName();
        final Long prodOriginPrice = product.getProdOriginPrice();
        final Long prodSalesPrice = product.getProdSalesPrice();
        final LocalDateTime prodRegDate = product.getProdRegDate();
        final String prodCategory = product.getProdCategory();
        final String prodMainImgPath = product.getProdMainImgPath();
        final String prodSalesDist = product.getProdSalesDist();
        final List<ProductOptionResponseDTO> productOptionResponseDTOs = product.getProductOptions().stream().map(com.amolli.oyeongshop.ver2.product.dto.ProductOptionResponseDTO::from)
                .collect(Collectors.toList());

        return new ProductOptionResponse(prodId, prodCode, prodName, prodOriginPrice,prodSalesPrice,prodRegDate,prodCategory,
                 prodMainImgPath, prodSalesDist, productOptionResponseDTOs);
    }
}
