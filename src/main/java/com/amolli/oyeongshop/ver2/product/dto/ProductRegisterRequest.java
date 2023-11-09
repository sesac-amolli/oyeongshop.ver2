package com.amolli.oyeongshop.ver2.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ProductRegisterRequest {
    @NotBlank
    private String prodName;
    @NotBlank
    private String prodCode;
    @NotBlank
    private int prodOriginPrice;
    @NotBlank
    private int prodSalesPrice;
    @NotBlank
    private String prodCategory;
    private String prodCategoryDetail;
    @NotBlank
    private String prodDesc;
}