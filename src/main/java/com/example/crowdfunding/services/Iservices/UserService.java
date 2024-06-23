package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.dtos.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UserPortfolioDTO> portfolio(String username);
    UserProfileDTO profile(String username) throws Exception;
    OtherProfileDTO otherProfile(String username) throws Exception;
    List<UserFollowersDTO> getFollowers(String username);
    List<UserFollowingsDTO> getFollowings(String username);
    void follow(String username ,String to_follow_username);
    void unfollow(String username ,String to_unfollow_username);

    ResponseEntity<AuthResponseDTO> editUserName(String username) throws Exception;

    void editPassword(String password);

    void editEmail(String email)throws Exception;

    void editPhone(String phone)throws Exception;
}
