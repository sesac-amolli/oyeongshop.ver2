package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDTO {
    private Long prodId;
    private String prodName;
    private String prodCode;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    private String prodDesc;
    private LocalDateTime prodRegDate;
    private LocalDateTime prodEditDate;


    public ProductDTO(Long prodId, String prodName, String prodCode, Long prodOriginPrice, Long prodSalesPrice, String prodCategory,
                      String prodDesc, LocalDateTime prodRegDate, LocalDateTime prodEditDate) {
        super();
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodCode = prodCode;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
        this.prodRegDate = prodRegDate;
        this.prodEditDate = prodEditDate;
    }

    public Product toEntity() {
        return Product.builder()
                .prodName(prodName)
                .prodCode(prodCode)
                .prodOriginPrice(prodOriginPrice)
                .prodSalesPrice(prodSalesPrice)
                .prodCategory(prodCategory)
                .prodDesc(prodDesc)
                .prodRegDate(prodRegDate)
                .prodEditDate(prodEditDate)
                .build();
    }
}
