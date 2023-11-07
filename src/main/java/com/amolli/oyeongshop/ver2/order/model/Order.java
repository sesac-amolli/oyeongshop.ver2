package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_order")
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

    private String orderAttnDetail;

    private String orderAttnRequest;

    private Long orderTotalPrice;

    @CreationTimestamp
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne // User 테이블 외래키 사용
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Order(Long orderId, Long orderNumber, String orderStatus, String orderAttnName, String orderAttnPhone, String orderAttnEmail, Long orderAttnPostcode, String orderAttnAddr1, String orderAttnAddr2, String orderAttnDetail, String orderAttnRequest, Long orderTotalPrice, LocalDate orderDate, List<OrderDetail> orderDetails, User user) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.orderAttnName = orderAttnName;
        this.orderAttnPhone = orderAttnPhone;
        this.orderAttnEmail = orderAttnEmail;
        this.orderAttnPostcode = orderAttnPostcode;
        this.orderAttnAddr1 = orderAttnAddr1;
        this.orderAttnAddr2 = orderAttnAddr2;
        this.orderAttnDetail = orderAttnDetail;
        this.orderAttnRequest = orderAttnRequest;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAttnName='" + orderAttnName + '\'' +
                ", orderAttnPhone='" + orderAttnPhone + '\'' +
                ", orderAttnEmail='" + orderAttnEmail + '\'' +
                ", orderAttnPostcode=" + orderAttnPostcode +
                ", orderAttnAddr1='" + orderAttnAddr1 + '\'' +
                ", orderAttnAddr2='" + orderAttnAddr2 + '\'' +
                ", orderAttnDetail='" + orderAttnDetail + '\'' +
                ", orderAttnRequest='" + orderAttnRequest + '\'' +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderDate=" + orderDate +
                ", orderDetails=" + orderDetails +
                ", user=" + user +
                '}';
    }
}
