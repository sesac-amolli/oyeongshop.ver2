package com.amolli.oyeongshop.ver2.order.model;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderPriceDTO;
import com.amolli.oyeongshop.ver2.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;

    private String orderNumber;

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

    private Long orderTotalOriginPrice;

    private Long orderTotalSalesPrice;

    private Long orderTotalPayment;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne // User 테이블 외래키 사용
    @JoinColumn(name = "user_id")
    private User user;

    public void setOrderNumbers(){
        // 오더 넘버 생성: 알파벳 2개 + 오늘 날짜 + UUID
        UUID uuid = UUID.randomUUID();
        // UUID를 문자열로 변환 후 알파벳 2개만 추출
        String alphaPrefix = uuid.toString().replaceAll("[^a-zA-Z]", "").substring(0, 2);
        // UUID를 문자열로 변환 후 5자리 숫자만 추출
        String uuidNumber = uuid.toString().replaceAll("[^0-9]", "").substring(0, 5);
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        this.orderNumber = alphaPrefix + todayDate + uuidNumber;;
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public void addOrderAddress(OrderDeliveryDTO orderDeliveryDTO){
        this.orderAttnName = orderDeliveryDTO.getOrderAttnName();
        this.orderAttnPhone = orderDeliveryDTO.getOrderAttnPhone();
        this.orderAttnPostcode = orderDeliveryDTO.getOrderAttnPostcode();
        this.orderAttnAddr1 = orderDeliveryDTO.getOrderAttnAddr1();
        this.orderAttnAddr2 = orderDeliveryDTO.getOrderAttnAddr2();
        this.orderAttnDetail = orderDeliveryDTO.getOrderAttnDetail();
        this.orderAttnRequest = orderDeliveryDTO.getOrderAttnRequest();
    }

    public long getOrderTotalSalesPrice(List<OrderDetail> orderDetails){
        long totalPrice = 0;
        for(OrderDetail orderDetail : orderDetails){
            totalPrice += orderDetail.getTotalSalesPrice();
        }
        return totalPrice;
    }
    public long getOrderOriginTotalPrice(List<OrderDetail> orderDetails){
        long totalPrice = 0;
        for(OrderDetail orderDetail : orderDetails){
            totalPrice += orderDetail.getTotalOriginPrice();
        }
        return totalPrice;
    }


    public static Order createOrder(User user, List<OrderDetail> orderDetails, OrderDeliveryDTO orderDeliveryDTO, OrderPriceDTO orderPriceDTO){
        Order order = new Order();
        order.setUser(user);
        for(OrderDetail orderDetail : orderDetails){
            order.addOrderDetail(orderDetail);
        }
        order.setOrderNumbers();
        order.addOrderAddress(orderDeliveryDTO);
        order.setOrderDate(LocalDateTime.now().withNano(0));
        order.setOrderStatus(OrderStatus.PAYMENT_ACCEPTED);
        order.setOrderTotalOriginPrice(order.getOrderOriginTotalPrice(orderDetails));
        order.setOrderTotalSalesPrice(order.getOrderTotalSalesPrice(orderDetails));
        order.setOrderTotalPayment(orderPriceDTO.getOrderTotalPayment());
        return order;
    }


}
