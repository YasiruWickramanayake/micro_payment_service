package com.micro.paymentservice.util.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PaymentPublisherMessage {
    private String sagaId;
    private Integer paymentStatus;

}
