package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProductResponse {
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    private String prodCategoryDetail;
    private String prodDesc;
    private String prodMainImgPath;
    private String prodSalesDist;
    private List<ProductOptionResponse> productOptionResponses;

    public ProductResponse(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, String prodCategory, String prodCategoryDetail, String prodMainImgPath, List<ProductOptionResponse> productOptionResponses) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodCategoryDetail = prodCategoryDetail;
        this.prodMainImgPath = prodMainImgPath;
        this.productOptionResponses = productOptionResponses;
    }

    public static ProductResponse from(Product product) {
        final Long prodId = product.getProdId();
        final String prodCode = product.getProdCode();
        final String prodName = product.getProdName();
        final Long prodOriginPrice = product.getProdOriginPrice();
        final Long prodSalesPrice = product.getProdSalesPrice();
        final String prodCategory = product.getProdCategory();
        final String prodCategoryDetail = product.getProdCategoryDetail();
        final String prodMainImgPath = product.getProdMainImgPath();
        final List<ProductOptionResponse> productOptionResponses = product.getProductOptions().stream().map(ProductOptionResponse::from)
                .collect(Collectors.toList());

        return new ProductResponse(prodId, prodCode, prodName, prodOriginPrice,prodSalesPrice,prodCategory,prodCategoryDetail,prodMainImgPath, productOptionResponses);
    }
}
