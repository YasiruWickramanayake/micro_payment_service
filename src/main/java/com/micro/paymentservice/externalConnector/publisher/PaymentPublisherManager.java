package com.micro.paymentservice.externalConnector.publisher;

import com.micro.paymentservice.util.common.PaymentPublisherMessage;

public interface PaymentPublisherManager {

    public void publishPaymentMessage(PaymentPublisherMessage paymentPublisherMessage);

}
