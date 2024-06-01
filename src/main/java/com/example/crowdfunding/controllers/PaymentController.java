package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.example.crowdfunding.utils.ApiUri.PAYMENT_URI;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(PAYMENT_URI)
public class PaymentController {
    @PostMapping("/pay")
    public PaymentDTO pay(Principal principal){
        return null;
    }
}
