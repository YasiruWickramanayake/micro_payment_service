package com.micro.paymentservice.externalConnector.publisher;

import com.micro.paymentService.PaymentFailRequest;
import com.micro.paymentService.PaymentSuccessRequest;
import com.micro.paymentservice.util.publisher.PaymentErrorPublisherMessage;
import com.micro.paymentservice.util.publisher.PaymentSuccessPublisherMessage;

public interface PaymentPublisher {

    public void publishPaymentSuccessMessage(PaymentSuccessRequest paymentSuccessRequest);

    public void publishPaymentFailMessage(PaymentFailRequest paymentFailRequest);
}
