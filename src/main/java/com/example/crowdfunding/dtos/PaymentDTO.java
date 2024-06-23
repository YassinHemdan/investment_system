package com.example.crowdfunding.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int id; // department id
    private int numberOfTokens;
    private String cardNumber;
    private String cardType;
}