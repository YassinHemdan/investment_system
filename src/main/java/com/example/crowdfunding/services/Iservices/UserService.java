package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.dtos.*;

import java.util.List;

public interface UserService {
    List<UserPortfolioDTO> portfolio(String username);
    UserProfileDTO profile(String username) throws Exception;
    OtherProfileDTO otherProfile(String username);
    List<UserFollowersDTO> getFollowers(String username);
    List<UserFollowingsDTO> getFollowings(String username);
    void follow(String username ,String to_follow_username);
    void unfollow(String username ,String to_unfollow_username);
}
