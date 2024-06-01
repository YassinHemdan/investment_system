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
public class UserController {
    private UserServiceImpl userService;
    @GetMapping("/portfolio")
    public List<UserPortfolioDTO> Portfolio(Principal principal){
        return userService.portfolio(principal.getName());
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
}
