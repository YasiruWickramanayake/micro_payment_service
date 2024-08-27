package com.micro.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String sagaId;
    private Integer customerId;
    private Double payableAmount;
}
