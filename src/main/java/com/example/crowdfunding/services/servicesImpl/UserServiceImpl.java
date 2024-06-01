package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.entities.Follower;
import com.example.crowdfunding.entities.UserEntity;
import com.example.crowdfunding.repos.FollowerRepo;
import com.example.crowdfunding.repos.UserRepo;
import com.example.crowdfunding.services.Iservices.UserService;
import com.example.crowdfunding.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final FollowerRepo followerRepo;
    private final Converter converter;

    private int getUserId(String username) throws Exception {
        return userRepo.findByUserName(username).orElseThrow(() -> new Exception("user not found")).getId();
    }
    @Override
    public List<UserPortfolioDTO> portfolio(String username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }
        return userRepo.portfolio(user_id);
    }

    @Override
    public UserProfileDTO profile(String username) throws Exception {
        int user_id = getUserId(username);
        UserEntity userEntity = userRepo.findById(user_id)
                .orElseThrow(() -> new Exception("user not found"));

        return converter.convert(userEntity, UserProfileDTO.class);
    }

    @Override
    public OtherProfileDTO otherProfile(String username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }

        List<UserPortfolioDTO> portfolio= userRepo.portfolio(user_id);
        OtherProfileDTO otherProfile = userRepo.otherProfile(user_id);
        otherProfile.setUserPortfolioDTO(portfolio);

        if(otherProfile == null)
            System.out.println("null ya 7abibi");

        return otherProfile;
    }

    @Override
    public List<UserFollowersDTO> getFollowers(String username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }
        return userRepo.getUserFollowers(user_id);
    }

    public List<UserFollowingsDTO> getFollowings(String username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }
        return userRepo.getUserFollowings(user_id);
    }

    @Override
    public void follow(String username, String to_follow_username) {
        // TODO make sure that both ids are not equal    " the user tries to follow him self "
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }
        int to_follow_id = 0;
        try {
            to_follow_id = getUserId(to_follow_username);
        }catch (Exception e){

        }

        UserEntity principal_user = new UserEntity();
        UserEntity to_follow_user = new UserEntity();

        principal_user.setId(user_id);
        to_follow_user.setId(to_follow_id);

        Follower follower = new Follower();
        follower.setFollower(principal_user);
        follower.setUser(to_follow_user);

        followerRepo.save(follower);
    }

    @Override
    public void unfollow(String username, String to_unfollow_username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }
        int to_unfollow_id = 0;
        try {
            to_unfollow_id = getUserId(to_unfollow_username);
        }catch (Exception e){

        }
        Follower to_delete  = followerRepo.findFollower(to_unfollow_id, user_id);
        followerRepo.delete(to_delete);
    }
}
