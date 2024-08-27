package com.micro.paymentservice.util.publisher;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentErrorPublisherMessage {
    private String sagaId;
}
