package com.example.crowdfunding.repos;

import com.example.crowdfunding.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
