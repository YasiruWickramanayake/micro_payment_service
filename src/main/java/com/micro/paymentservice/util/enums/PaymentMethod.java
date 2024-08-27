package com.micro.paymentservice.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CARD_PAYMENT(1, "Card Payment"),
    CASH_PAYMENT(2, "Cash payment");

    private final Integer paymentMethodId;
    private final String paymentMethodNarration;

}
