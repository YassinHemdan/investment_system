package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.entities.Follower;
import com.example.crowdfunding.entities.UserEntity;
import com.example.crowdfunding.repos.FollowerRepo;
import com.example.crowdfunding.repos.PaymentRepo;
import com.example.crowdfunding.repos.UserRepo;
import com.example.crowdfunding.services.Iservices.UserService;
import com.example.crowdfunding.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final FollowerRepo followerRepo;
    private final PaymentRepo paymentRepo;
    private final Converter converter;
    private final AuthenticationServiceImpl authenticationService;
    private int getUserId(String username) throws Exception {
        return userRepo.findByUserName(username).orElseThrow(() -> new Exception("user not found")).getId();
    }
    private boolean isAFollower(int user_id, int follower_id){
        return followerRepo.findFollower(user_id, follower_id) != null ;
    }
    private boolean isAFollowee(int user_id, int follower_id){
        return followerRepo.findFollower(user_id, follower_id) != null ;
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
    public OtherProfileDTO otherProfile(String username) throws Exception {
        String u = SecurityContextHolder.getContext().getAuthentication().getName();
        int uid = getUserId(u);

        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }

        List<UserPortfolioDTO> portfolio= userRepo.portfolio(user_id);
        OtherProfileDTO otherProfile = userRepo.otherProfile(user_id);
        otherProfile.setUserPortfolioDTO(portfolio);

//        if(otherProfile == null)
//            System.out.println("null ya 7abibi");

        otherProfile.setAFollower(isAFollower(uid, user_id));
        otherProfile.setAFollowee(isAFollowee(user_id, uid));

        return otherProfile;
    }

    @Override
    public List<UserFollowersDTO> getFollowers(String username) {
        int user_id = 0;
        try {
            user_id = getUserId(username);
        }catch (Exception e){

        }

        List<UserFollowersDTO> followersDTOS =  userRepo.getUserFollowers(user_id);
        for(UserFollowersDTO userFollowersDTO : followersDTOS){
            int follower_id = userFollowersDTO.getId();
            userFollowersDTO.setFollowing(isAFollowee(follower_id, user_id));
        }

        return followersDTOS;
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

    @Override
    public ResponseEntity<AuthResponseDTO> editUserName(String username) throws Exception {
        if(userRepo.findByUserName(username).isPresent())
            throw new RuntimeException("username already exists");

        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUserName(current_username).orElseThrow();

        user.setUserName(username);

        userRepo.save(user);

        Optional<UserEntity> updatedUser = userRepo.findByUserName(username);
        String password = updatedUser.orElseThrow().getPassword();
        LoginDTO loginDTO = new LoginDTO(username, password);
        return authenticationService.login(loginDTO);
    }

    @Override
    public void editPassword(String password) {
        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUserName(current_username).orElseThrow();

        user.setPassword(password);

        userRepo.save(user);
    }

    @Override
    public void editEmail(String email) throws Exception {
        if(userRepo.findByEmail(email) != null)
            throw new RuntimeException("email is already exists");

        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUserName(current_username).orElseThrow();

        user.setEmail(email);

        userRepo.save(user);
    }

    @Override
    public void editPhone(String phone) throws Exception{
        if(userRepo.findByPhone(phone) != null){
            throw new RuntimeException("phone is already exists");
        }
        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUserName(current_username).orElseThrow();

        user.setPhone(phone);

        userRepo.save(user);
    }
}
