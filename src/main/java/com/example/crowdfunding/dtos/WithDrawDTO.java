package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WithDrawDTO {
    private String id; // to get the credit card
    private double amount;
}
