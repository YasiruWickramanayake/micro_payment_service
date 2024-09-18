package com.micro.paymentservice.externalConnectorImpl.publisherImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micro.paymentService.*;
import com.micro.paymentservice.externalConnector.publisher.PaymentPublisher;
import com.micro.paymentservice.util.publisher.PaymentErrorPublisherMessage;
import com.micro.paymentservice.util.publisher.PaymentSuccessPublisherMessage;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class PaymentPublisherImpl implements PaymentPublisher {

    @GrpcClient("payment-success-publisher")
    PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStubSuccess;

    @GrpcClient("payment-fail-publisher")
    PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStubFail;
    @Override
    public void publishPaymentSuccessMessage(PaymentSuccessRequest paymentSuccessRequest) {
        try {
            PaymentSuccessResponse paymentSuccessResponse =
                    paymentServiceBlockingStubSuccess.paymentSuccess(paymentSuccessRequest);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void publishPaymentFailMessage(PaymentFailRequest paymentFailRequest) {
        try {
            PaymentFailResponse paymentFailResponse =
                    paymentServiceBlockingStubFail.paymentFail(paymentFailRequest);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
