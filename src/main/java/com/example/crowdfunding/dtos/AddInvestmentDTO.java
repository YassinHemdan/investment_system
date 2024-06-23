package com.example.crowdfunding.dtos;


import com.example.crowdfunding.entities.Department;
import com.example.crowdfunding.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddInvestmentDTO {
    private UserEntity user;
    private Department department;
    private LocalDate date_of_investment;
    private LocalTime time_of_investment;
}
