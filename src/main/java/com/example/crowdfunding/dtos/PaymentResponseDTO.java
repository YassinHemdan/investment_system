package com.example.crowdfunding.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentResponseDTO {
    private String message;
    private HttpStatus httpStatus;
    private boolean paymentStatus;
}
