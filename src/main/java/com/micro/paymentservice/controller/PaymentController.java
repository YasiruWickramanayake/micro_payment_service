package com.micro.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.paymentservice.dto.PaymentRequest;
import com.micro.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/card-payment")
    public String processCardPaymanet(@RequestBody String cardPaymentInitiate) throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();
        PaymentRequest paymentRequest = ob.readValue(cardPaymentInitiate, PaymentRequest.class);
        paymentService.initiateCardPayment(paymentRequest);
        return "payment initaited";
    }
}
