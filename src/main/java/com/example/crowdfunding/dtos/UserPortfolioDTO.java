package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPortfolioDTO { // returned to admin dashboard
    private int id;
    private String country;
    private String city;
    private String street;
    private double area;
    private double availableArea;
    private double price;
    private double tokenPrice;
    private int ownedTokens;
    // TODO: add a double attribute that contains the -> Total Invested Money
}
