package com.micro.paymentservice.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PAYMENT_SUCCESS(1, "Payment is success"),
    PAYMENT_FAIL(2, "Payment is failed"),
    PAYMENT_HOLD(3, "Payment is hold");

    private final Integer statusId;
    private final String message;
}
