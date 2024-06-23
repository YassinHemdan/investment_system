package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtherProfileDTO {
    private String userName;
    private int numberOfFollowers;
    private boolean isAFollower;
    private boolean isAFollowee;
    private List<UserPortfolioDTO> userPortfolioDTO;
}
