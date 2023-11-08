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

@Builder
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne // User 테이블 외래키 사용
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
        user.getOrders().add(this);
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
