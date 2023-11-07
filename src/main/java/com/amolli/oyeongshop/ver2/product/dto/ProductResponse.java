package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

    @Builder // 그렇다면 왜 Builder를 쓰는 것인가?
    public ProductResponse(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, String prodCategory, String prodCategoryDetail, String prodDesc, String prodMainImgPath, String prodSalesDist) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodCategoryDetail = prodCategoryDetail;
        this.prodDesc = prodDesc;
        this.prodMainImgPath = prodMainImgPath;
        this.prodSalesDist = prodSalesDist;
    }

    public ProductResponse(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, String prodCategory, String prodCategoryDetail, String prodMainImgPath) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodCategoryDetail = prodCategoryDetail;
        this.prodMainImgPath = prodMainImgPath;
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

        return new ProductResponse(prodId, prodCode, prodName, prodOriginPrice,prodSalesPrice,prodCategory,prodCategoryDetail,prodMainImgPath);
    }
}
