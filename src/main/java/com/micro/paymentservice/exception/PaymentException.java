package com.micro.paymentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentException extends RuntimeException{

    private Integer errorCode;
    private String message;
}
