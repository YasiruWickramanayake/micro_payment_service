package com.micro.paymentservice.externalConnector.publisher;

import com.micro.paymentservice.util.publisher.PaymentErrorPublisherMessage;
import com.micro.paymentservice.util.publisher.PaymentSuccessPublisherMessage;

public interface PaymentPublisher {

    public void publishPaymentSuccessMessage(PaymentSuccessPublisherMessage paymentSuccessPublisherMessage);

    public void publishPaymentFailMessage(PaymentErrorPublisherMessage paymentErrorPublisherMessage);
}
