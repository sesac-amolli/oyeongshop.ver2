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
public class OrderDetail {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderDetailId;

    private Long orderDetailAmount;

    private Long orderDetailOriginPrice;

    private Long orderDetailSalesPrice;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "prod_opt_id")
    private ProductOption productOption;

    public static OrderDetail createOrderDetail(ProductOption productOption, Long count, Long orderDetailSalesPrice, Long orderDetailOriginPrice){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setProductOption(productOption);
        orderDetail.setOrderDetailAmount(count);
        orderDetail.setOrderDetailSalesPrice(orderDetailSalesPrice);
        orderDetail.setOrderDetailOriginPrice(orderDetailOriginPrice);
        return orderDetail;
    }

    public long getTotalSalesPrice(){
        return orderDetailSalesPrice*orderDetailAmount;
    }

    public long getTotalOriginPrice(){
        return orderDetailOriginPrice*orderDetailAmount;
    }
}
