package com.micro.paymentservice.service;

import com.micro.paymentservice.dto.PaymentRequest;

public interface PaymentService{

    public void initiateCardPayment(PaymentRequest paymentRequest);
}
