package com.amolli.oyeongshop.ver2.product.model;

import lombok.AccessLevel;
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
    private Long prodOptSize;
    private Long prodOptAmount;

    @ManyToOne
    @JoinColumn(name ="prod_id")
    private Product product;

}
