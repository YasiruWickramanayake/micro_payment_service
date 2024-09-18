package com.micro.paymentservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private String sagaId;
    private Integer customerId;
    private Double payableAmount;
}
