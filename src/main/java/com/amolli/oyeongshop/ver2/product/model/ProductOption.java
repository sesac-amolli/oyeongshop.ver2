package com.amolli.oyeongshop.ver2.product.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "tblProductOption")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodOptId;

    private String prodOptColor;

    private Long prodOptSize;

    private Long prodOptAmount;

    private Long prodId;

//    @ManyToOne
//    @JoinColumn(name ="prod_id")
//    private Product product;
}
