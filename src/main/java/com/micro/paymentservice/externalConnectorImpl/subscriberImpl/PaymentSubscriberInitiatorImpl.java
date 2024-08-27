package com.micro.paymentservice.externalConnectorImpl.subscriberImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.paymentservice.dto.PaymentRequest;
import com.micro.paymentservice.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentSubscriberInitiatorImpl {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "init-payment")
    public void paymentInitiateMessage(ConsumerRecord<String, String> record) {
        try {
            System.out.println("Received message: " + record.value());
            ObjectMapper ob = new ObjectMapper();
            PaymentRequest paymentRequest = ob.readValue(record.value(), PaymentRequest.class);
            paymentService.initiateCardPayment(paymentRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
