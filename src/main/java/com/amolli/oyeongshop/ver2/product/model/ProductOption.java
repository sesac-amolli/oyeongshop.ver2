package com.amolli.oyeongshop.ver2.product.model;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "tblProductOption")
    private OrderDetail orderDetail;

    @OneToMany(mappedBy = "tblProductOption")
    private Cart cart;
}
