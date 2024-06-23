package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResponseDTO {
    private String res;
    private HttpStatus httpStatus;

}
