package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.dtos.PaymentDTO;

public interface PaymentService {
    String Pay(String username, PaymentDTO paymentDTO);
}
