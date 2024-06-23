package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.services.servicesImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.example.crowdfunding.utils.ApiUri.USER_URI;

@RestController
@RequestMapping(USER_URI)
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserServiceImpl userService;
    @GetMapping("/portfolio")
    public List<UserPortfolioDTO> Portfolio(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.portfolio(username);
    }
    @GetMapping("/profile")
    public UserProfileDTO Profile(Principal principal) throws Exception {
        return userService.profile(principal.getName());
    }
    @GetMapping("/followers")
    public List<UserFollowersDTO> getFollowers(Principal principal){
        return userService.getFollowers(principal.getName());
    }
    @GetMapping("/followings")
    public List<UserFollowingsDTO> getFollowings(Principal principal){
        return userService.getFollowings(principal.getName());
    }
    @PostMapping("/follow/{username}")
    public ResponseEntity<String> follow(@PathVariable(name = "username") String username){
        var principal_name = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.follow(principal_name, username);
        return new ResponseEntity<>("You have followed " + username, HttpStatus.OK);
    }
    @DeleteMapping("/unfollow/{username}")
    public ResponseEntity<String> unfollow(@PathVariable(name = "username") String username){
        var principal_name = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.unfollow(principal_name, username);
        return new ResponseEntity<>("You have unfollowed " + username, HttpStatus.OK);
    }


    // TODO implement the following methods
    @PutMapping("/edit-username/{username}")
    public ResponseEntity<AuthResponseDTO> editUserName(@PathVariable(name = "username") String newUserName) throws Exception{
            return userService.editUserName(newUserName);
    }
    @PutMapping("/edit-password/{password}")
    public TestResponseDTO editPassword(@PathVariable(name = "password") String password){
        userService.editPassword(password);
        return new TestResponseDTO("password updated successfully", HttpStatus.OK);
    }
    @PutMapping("/edit-email/{email}")
    public TestResponseDTO editEmail(@PathVariable(name = "email") String email){
        try {
            userService.editEmail(email);
        }catch (Exception e){
            return new TestResponseDTO("email is already exists", HttpStatus.BAD_REQUEST);
        }
        return new TestResponseDTO("email updated successfully", HttpStatus.OK);
    }
    @PutMapping("/edit-phone/{phone}")
    public TestResponseDTO editPhone(@PathVariable(name = "phone") String phone){
        try{
            userService.editPhone(phone);
        }catch (Exception e){
            return new TestResponseDTO("phone is already exists" ,HttpStatus.BAD_REQUEST);
        }
        return new TestResponseDTO("phone updated successfully", HttpStatus.OK);
    }
}
