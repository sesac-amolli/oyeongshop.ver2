package com.amolli.oyeongshop.ver2.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long paymentId;

    private String paymentPc;

    private String paymentMathod;

    private Date paymentDate;

    private  int paymentTotalPrice;

    private int paymentCashPrice;

    private String paymentPayerName;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}