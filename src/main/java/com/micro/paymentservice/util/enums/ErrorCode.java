package com.micro.paymentservice.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CUSTOMER_CARD_DETAILS_DOES_NOT_EXIST_IN_DB(401, "Customer card details does not exist in DB");

    private final Integer errorCode;
    private final String message;
}
