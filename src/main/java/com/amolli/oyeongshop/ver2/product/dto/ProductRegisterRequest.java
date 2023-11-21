package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
public class ProductRegisterRequest {
    @NotBlank
    private String prodName;
    @NotBlank
    private String prodCode;
    @NotBlank
    private long prodOriginPrice;
    @NotBlank
    private long prodSalesPrice;
    @NotBlank
    private String prodCategory;


    @NotBlank
    private String prodDesc;
    @NotBlank
    private String prodSalesDist;

    public ProductRegisterRequest(String prodName, String prodCode, long prodOriginPrice, long prodSalesPrice,
                                  String prodCategory, String prodDesc, String prodSalesDist) {
        this.prodName = prodName;
        this.prodCode = prodCode;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
        this.prodSalesDist = prodSalesDist;
    }
}