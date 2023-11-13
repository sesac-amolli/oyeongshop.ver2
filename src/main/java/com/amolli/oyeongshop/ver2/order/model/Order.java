package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDto;
import com.amolli.oyeongshop.ver2.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Parameter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_number", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_number_generator")
    @GenericGenerator(
            name = "order_number_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "order_number_sequence"),
                    @Parameter(name = "initial_value", value = "10000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String orderAttnName;

    private String orderAttnPhone;

    private String orderAttnEmail;

    private String orderAttnPostcode;

    private String orderAttnAddr1;

    private String orderAttnAddr2;

    private String orderAttnDetail;

    private String orderAttnRequest;

    private Long orderTotalPrice;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne // User 테이블 외래키 사용
    @JoinColumn(name = "user_id")
    private User user;

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public void addOrderAddress(OrderDeliveryDto orderDeliveryDTO){
        this.orderAttnName = orderDeliveryDTO.getOrderAttnName();
        this.orderAttnPhone = orderDeliveryDTO.getOrderAttnPhone();
        this.orderAttnPostcode = orderDeliveryDTO.getOrderAttnPostcode();
        this.orderAttnAddr1 = orderDeliveryDTO.getOrderAttnAddr1();
        this.orderAttnAddr2 = orderDeliveryDTO.getOrderAttnAddr2();
        this.orderAttnDetail = orderDeliveryDTO.getOrderAttnDetail();
        this.orderAttnRequest = orderDeliveryDTO.getOrderAttnRequest();
    }

    public long getTotalPrice(){
        long totalPrice = 0;
        for(OrderDetail orderDetail : orderDetails){
            totalPrice += orderDetail.getTotalPrice();
        }
        return totalPrice;
    }

    public static Order createOrder(User user, List<OrderDetail> orderDetails, OrderDeliveryDto orderDeliveryDTO){
        Order order = new Order();
        order.setUser(user);
        for(OrderDetail orderDetail : orderDetails){
            order.addOrderDetail(orderDetail);
        }
        order.addOrderAddress(orderDeliveryDTO);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PAYMENT_ACCEPTED);
        order.setOrderTotalPrice(order.getTotalPrice());
        return order;
    }


    @Builder
    public Order(Long orderId, Long orderNumber, OrderStatus orderStatus, String orderAttnName, String orderAttnPhone, String orderAttnEmail, String orderAttnPostcode, String orderAttnAddr1, String orderAttnAddr2, String orderAttnDetail, String orderAttnRequest, Long orderTotalPrice, LocalDateTime orderDate, List<OrderDetail> orderDetails, User user) {
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
}
