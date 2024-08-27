package com.micro.paymentservice.externalConnectorImpl.publisherImpl;

import com.micro.paymentservice.externalConnector.publisher.PaymentPublisher;
import com.micro.paymentservice.externalConnector.publisher.PaymentPublisherManager;
import com.micro.paymentservice.util.common.PaymentPublisherMessage;
import com.micro.paymentservice.util.enums.PaymentStatus;
import com.micro.paymentservice.util.publisher.PaymentErrorPublisherMessage;
import com.micro.paymentservice.util.publisher.PaymentSuccessPublisherMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PaymentPublisherManagerImpl implements PaymentPublisherManager {

    @Autowired
    private PaymentPublisher paymentPublisher;

    @Override
    public void publishPaymentMessage(PaymentPublisherMessage paymentPublisherMessage) {
        if(paymentPublisherMessage.getPaymentStatus().equals(PaymentStatus.PAYMENT_SUCCESS.getStatusId())){
                this.publishPaymentSuccessMessage(paymentPublisherMessage);
        } else if (paymentPublisherMessage.getPaymentStatus().equals(PaymentStatus.PAYMENT_FAIL.getStatusId())) {
                this.publishPaymentFailMessage(paymentPublisherMessage);
        }
    }

    private void publishPaymentSuccessMessage(PaymentPublisherMessage paymentPublisherMessage){
        PaymentSuccessPublisherMessage paymentSuccessPublisherMessage =PaymentSuccessPublisherMessage.builder()
                .sagaId(paymentPublisherMessage.getSagaId())
                .build();

        paymentPublisher.publishPaymentSuccessMessage(paymentSuccessPublisherMessage);
    }

    private void publishPaymentFailMessage(PaymentPublisherMessage paymentPublisherMessage){
        PaymentErrorPublisherMessage paymentErrorPublisherMessage = PaymentErrorPublisherMessage.builder()
                .sagaId(paymentPublisherMessage.getSagaId())
                .build();

        paymentPublisher.publishPaymentFailMessage(paymentErrorPublisherMessage);
    }
}
