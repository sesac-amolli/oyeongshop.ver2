package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tblOrder")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;

    private Long orderNumber;

    private String orderStatus;

    private String orderAttnName;

    private String orderAttnPhone;

    private String orderAttnEmail;

    private Long orderAttnPostcode;

    private String orderAttnAddr1;

    private String orderAttnAddr2;

    private String orderAttnAddr3;

    private String orderAttnDetail;

    private String orderAttnRequest;

    private Long orderTotalPrice;

    private Date orderDate;

    @OneToMany(mappedBy = "orderDetail")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    //@ManyToOne - User 테이블 외래키 사용
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;

}
