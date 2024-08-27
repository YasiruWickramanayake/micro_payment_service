package com.micro.paymentservice.serviceImpl;

import com.micro.paymentservice.dao.CustomerCardDetailsRepository;
import com.micro.paymentservice.dao.PaymentRepository;
import com.micro.paymentservice.dto.PaymentRequest;
import com.micro.paymentservice.entity.CustomerCardDetails;
import com.micro.paymentservice.entity.Payment;
import com.micro.paymentservice.exception.PaymentException;
import com.micro.paymentservice.externalConnector.publisher.PaymentPublisherManager;
import com.micro.paymentservice.service.PaymentService;
import com.micro.paymentservice.util.common.PaymentPublisherMessage;
import com.micro.paymentservice.util.enums.ErrorCode;
import com.micro.paymentservice.util.enums.PaymentMethod;
import com.micro.paymentservice.util.enums.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CustomerCardDetailsRepository customerCardDetailsRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentPublisherManager paymentPublisherManager;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void initiateCardPayment(PaymentRequest paymentRequest) {
        try{
            // get customer card details
            CustomerCardDetails customerCardDetailsByCustomerId = getCustomerCardDetailsByCustomerId(paymentRequest.getCustomerId());
            Payment payment = this.generatePayment(customerCardDetailsByCustomerId, paymentRequest);
            savePaymentDetails(payment);

            // send payment success message queue
            paymentPublisherManager.publishPaymentMessage(
                    this.generatePaymentPublisherMessage(paymentRequest.getSagaId(),
                            PaymentStatus.PAYMENT_SUCCESS.getStatusId()));
        }catch (RuntimeException ex){
            // send payment fail message queue
            paymentPublisherManager.publishPaymentMessage(
                    this.generatePaymentPublisherMessage(paymentRequest.getSagaId(),
                            PaymentStatus.PAYMENT_FAIL.getStatusId()));
        }
    }

    private void savePaymentDetails(Payment payment) throws RuntimeException{
        paymentRepository.save(payment);
    }


    private CustomerCardDetails getCustomerCardDetailsByCustomerId(Integer customerId){
        return customerCardDetailsRepository
                .findByCustomerId(customerId)
                .orElseThrow(() ->
                        new PaymentException(ErrorCode.CUSTOMER_CARD_DETAILS_DOES_NOT_EXIST_IN_DB.getErrorCode(),
                                ErrorCode.CUSTOMER_CARD_DETAILS_DOES_NOT_EXIST_IN_DB.getMessage()));
    }


    private Payment generatePayment(CustomerCardDetails customerCardDetailsByCustomerId,
                                    PaymentRequest paymentRequest){


        return Payment.builder()
                .paymentMethod(PaymentMethod.CARD_PAYMENT.getPaymentMethodId())
                .amount(paymentRequest.getPayableAmount())
                .sagaId(paymentRequest.getSagaId())
                .customerId(paymentRequest.getCustomerId())
                .createdDate(new Date(new java.util.Date().getTime()))
                .customerCardDetails(customerCardDetailsByCustomerId)
                .status(PaymentStatus.PAYMENT_SUCCESS.getStatusId())
                .narration(PaymentStatus.PAYMENT_SUCCESS.getMessage())
                .build();
    }

    private PaymentPublisherMessage generatePaymentPublisherMessage(String sagaId, Integer paymentStatus){
        return PaymentPublisherMessage.builder()
                .sagaId(sagaId)
                .paymentStatus(paymentStatus)
                .build();
    }
}
