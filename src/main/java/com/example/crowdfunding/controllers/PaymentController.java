package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.PaymentDTO;
import com.example.crowdfunding.dtos.PaymentResponseDTO;
import com.example.crowdfunding.dtos.TestResponseDTO;
import com.example.crowdfunding.services.servicesImpl.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.crowdfunding.utils.ApiUri.PAYMENT_URI;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(PAYMENT_URI)
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
    private final PaymentServiceImpl paymentService;
    @PostMapping("/pay")
    public PaymentResponseDTO pay(@RequestBody PaymentDTO paymentDTO){
        String res = paymentService.Pay(SecurityContextHolder.getContext().getAuthentication().getName(), paymentDTO);
        if(res.equals("Success"))
            return new PaymentResponseDTO( "Success", HttpStatus.OK, true);
        else return new PaymentResponseDTO( "Payment failed " + res, HttpStatus.BAD_REQUEST, false);
    }
}
