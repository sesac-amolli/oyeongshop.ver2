package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name="tblOrderDetail")
@NoArgsConstructor
public class OrderDetail {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderDetailId;

    private Long ordeDetailAmount;

    private Long orderDetailPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "prod_opt_id")
    private ProductOption productOption;
}
