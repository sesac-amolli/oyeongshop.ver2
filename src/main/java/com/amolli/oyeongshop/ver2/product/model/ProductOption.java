package com.amolli.oyeongshop.ver2.product.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_product_option")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@DynamicUpdate
@DynamicInsert
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodOptId;
    private String prodOptColor;
    private String prodOptSize;
    private Long prodOptAmount;

    @ManyToOne(fetch=FetchType.LAZY)
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
