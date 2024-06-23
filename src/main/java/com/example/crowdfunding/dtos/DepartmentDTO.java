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
public class DepartmentDTO {
    private int id;
    private String country;
    private String city;
    private String street;
    private double price;
    private double area;
}
