package com.amolli.oyeongshop.ver2.product.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tbl_product_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodDetailImgId;

    private String prodDetailImgName;

    @ManyToOne
    @JoinColumn(name ="prod_id")
    private Product product;

}
