package com.micro.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "customer_card_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "customerId")
    private Integer customerId;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "pin_number")
    private String pinNumber;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "exp_date")
    private Date expDate;

    @OneToMany(mappedBy = "customerCardDetails", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Payment> payment;

}
