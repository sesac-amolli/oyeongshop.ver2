package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="tbl_order_detail")
@NoArgsConstructor
@Builder
public class OrderDetail {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderDetailId;

    private Long ordeDetailAmount;

    private Long orderDetailPrice;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "prod_opt_id")
    private ProductOption productOption;

    public static OrderDetail createOrderDetail(ProductOption productOption, long count){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductOption(productOption);
        orderDetail.setOrdeDetailAmount(count);
        //orderDetail.setOrderDetailPrice(productOption.getProduct().getProdSalesPrice);
        return orderDetail;
    }
}
