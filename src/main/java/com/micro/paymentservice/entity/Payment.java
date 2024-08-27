package com.micro.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "saga_id")
    private String sagaId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_details", referencedColumnName = "payment_id")
    private CustomerCardDetails customerCardDetails;

    @Column(name = "payment_status")
    private Integer status;

    @Column(name = "narration")
    private String narration;

    @Column(name = "created_date")
    private Date createdDate;
}
