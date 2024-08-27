package com.micro.paymentservice.externalConnectorImpl.publisherImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micro.paymentservice.externalConnector.publisher.PaymentPublisher;
import com.micro.paymentservice.util.publisher.PaymentErrorPublisherMessage;
import com.micro.paymentservice.util.publisher.PaymentSuccessPublisherMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class PaymentPublisherImpl implements PaymentPublisher {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publishPaymentSuccessMessage(PaymentSuccessPublisherMessage paymentSuccessPublisherMessage) {
        try {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        json = ow.writeValueAsString(paymentSuccessPublisherMessage);
        kafkaTemplate.send("payment-success", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void publishPaymentFailMessage(PaymentErrorPublisherMessage paymentErrorPublisherMessage) {
        try {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        json = ow.writeValueAsString(paymentErrorPublisherMessage);
        kafkaTemplate.send("payment-fail", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
