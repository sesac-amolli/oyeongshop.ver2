package com.amolli.oyeongshop.ver2.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long paymentId;
    private String paymentImpUid;
    private String paymentPg;
    private String paymentApplyNum;
    private String paymentMethod;
    private String paymentCardNum;
    private LocalDate paymentDate;
    private  int paymentTotalPrice;
    private int paymentCashPrice;
    private String paymentPayerName;
    private String paymentReceipt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public void setOrder(Order order) {
       this.order = order;
    }
}
