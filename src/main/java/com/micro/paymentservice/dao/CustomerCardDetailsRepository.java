package com.micro.paymentservice.dao;

import com.micro.paymentservice.entity.CustomerCardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCardDetailsRepository extends JpaRepository<CustomerCardDetails, Integer> {

    Optional<CustomerCardDetails> findByCustomerId(Integer integer);
}
