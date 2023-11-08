package com.amolli.oyeongshop.ver2.product.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tbl_product_option")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;

    @ManyToOne
    @JoinColumn(name ="prod_id")
    private Product product;

    @Builder
    public ProductOption(Long prodOptId, String prodOptColor, String prodOptSize, Long prodOptAmount, Product product) {
        this.prodOptId = prodOptId;
        this.prodOptColor = prodOptColor;
        this.prodOptSize = prodOptSize;
        this.prodOptAmount = prodOptAmount;
        this.product = product;
    }
}
