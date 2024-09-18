package com.micro.paymentservice.externalConnectorImpl.subscriberImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.paymentService.PaymentInitiateRequest;
import com.micro.paymentService.PaymentInitiateResponse;
import com.micro.paymentService.PaymentServiceGrpc;
import com.micro.paymentservice.dto.PaymentRequest;
import com.micro.paymentservice.service.PaymentService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@GrpcService
public class PaymentSubscriberInitiatorImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    @Autowired
    private PaymentService paymentService;

    @Override
    public void paymentInitiate(PaymentInitiateRequest request, StreamObserver<PaymentInitiateResponse> responseObserver) {
        try{
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .sagaId(request.getSagaId())
                    .customerId(request.getCustomerId())
                    .payableAmount(request.getPayableAmount())
                    .build();
            paymentService.initiateCardPayment(paymentRequest);
            responseObserver.onNext(PaymentInitiateResponse.newBuilder()
                    .setStatus(true)
                    .setMessage("message received successfully")
                    .build());
            responseObserver.onCompleted();
        }catch (Exception ex){
            responseObserver.onNext(PaymentInitiateResponse.newBuilder()
                    .setStatus(false)
                    .setMessage("message received failed")
                    .build());
            responseObserver.onCompleted();
        }
    }

//    @KafkaListener(topics = "init-payment")
//    public void paymentInitiateMessage(ConsumerRecord<String, String> record) {
//        try {
//            System.out.println("Received message: " + record.value());
//            ObjectMapper ob = new ObjectMapper();
//            PaymentRequest paymentRequest = ob.readValue(record.value(), PaymentRequest.class);
//            paymentService.initiateCardPayment(paymentRequest);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
