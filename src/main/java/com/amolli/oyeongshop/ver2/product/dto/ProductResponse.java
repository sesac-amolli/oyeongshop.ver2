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
public class ProductResponse {
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    private String prodCategoryDetail;
    private String prodDesc;
    private LocalDateTime prodRegDate;
    private String prodMainImgPath;
    private String prodSalesDist;
    private List<ProductOptionResponse> productOptionResponses;

    public ProductResponse(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, LocalDateTime prodRegDate, String prodCategory, String prodCategoryDetail, String prodMainImgPath, String prodSalesDist, List<ProductOptionResponse> productOptionResponses) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodRegDate = prodRegDate;
        this.prodCategory = prodCategory;
        this.prodCategoryDetail = prodCategoryDetail;
        this.prodMainImgPath = prodMainImgPath;
        this.prodSalesDist = prodSalesDist;
        this.productOptionResponses = productOptionResponses;
    }

    public static ProductResponse from(Product product) {
        final Long prodId = product.getProdId();
        final String prodCode = product.getProdCode();
        final String prodName = product.getProdName();
        final Long prodOriginPrice = product.getProdOriginPrice();
        final Long prodSalesPrice = product.getProdSalesPrice();
        final LocalDateTime prodRegDate = product.getProdRegDate();
        final String prodCategory = product.getProdCategory();
        final String prodCategoryDetail = product.getProdCategoryDetail();
        final String prodMainImgPath = product.getProdMainImgPath();
        final String prodSalesDist = product.getProdSalesDist();
        final List<ProductOptionResponse> productOptionResponses = product.getProductOptions().stream().map(ProductOptionResponse::from)
                .collect(Collectors.toList());

        return new ProductResponse(prodId, prodCode, prodName, prodOriginPrice,prodSalesPrice,prodRegDate,prodCategory,prodCategoryDetail,prodMainImgPath, prodSalesDist, productOptionResponses);
    }
}
