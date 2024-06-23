package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersDTO {
    private int id;
    private String userName;
    private boolean following;

    public UserFollowersDTO(int id, String userName) {
        this.userName = userName;
        this.id = id;
    }
}
