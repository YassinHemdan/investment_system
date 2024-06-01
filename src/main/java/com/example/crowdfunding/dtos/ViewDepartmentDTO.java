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
public class ViewDepartmentDTO {
    private int id;
    private String country;
    private String city;
    private String street;
    private double area;
    private double availableArea;
    private BigDecimal price;
    private BigDecimal tokenPrice;
}
