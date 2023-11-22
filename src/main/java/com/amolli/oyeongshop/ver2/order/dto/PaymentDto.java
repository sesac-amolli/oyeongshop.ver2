package com.amolli.oyeongshop.ver2.order.dto;
import com.amolli.oyeongshop.ver2.order.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PaymentDto {
    private String paymentImpUid;
    private String paymentPg;
    private String paymentApplyNum;
    private String paymentMethod;
    private String paymentCardNum;
    private LocalDate paymentDate;
    private  String paymentTotalPrice;
    private int paymentCashPrice;
    private String paymentPayerName;
    private String paymentReceipt;
    private String orderId;

    public Payment toEntity() {
        return Payment.builder()
                .paymentImpUid(paymentImpUid)
                .paymentPg(paymentPg)
                .paymentApplyNum(paymentApplyNum)
                .paymentMethod(paymentMethod)
                .paymentCardNum(paymentCardNum)
                .paymentDate(paymentDate)
                .paymentTotalPrice(Integer.parseInt(paymentTotalPrice))
                .paymentCashPrice(paymentCashPrice)
                .paymentPayerName(paymentPayerName)
                .paymentReceipt(paymentReceipt)
                .build();
    }
}
