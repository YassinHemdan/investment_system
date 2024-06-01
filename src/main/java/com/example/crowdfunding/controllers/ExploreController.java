package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.DepartmentDTO;
import com.example.crowdfunding.dtos.OtherProfileDTO;
import com.example.crowdfunding.dtos.InvestorDTO;
import com.example.crowdfunding.dtos.ViewDepartmentDTO;

import com.example.crowdfunding.services.servicesImpl.ExploreServiceImpl;
import com.example.crowdfunding.services.servicesImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.crowdfunding.utils.ApiUri.EXPLORE_URI;
import java.util.List;

@RequestMapping(EXPLORE_URI)
@AllArgsConstructor
@RestController
public class ExploreController {
    private final UserServiceImpl userService;
    private final ExploreServiceImpl exploreService;
    @GetMapping("/{username}/portfolio")
    public OtherProfileDTO viewUserPortfolio(@PathVariable(name = "username") String username){
        return userService.otherProfile(username);
    }

    @GetMapping("/department/{id}")
    public ViewDepartmentDTO viewDepartment(@PathVariable(name = "id") int id){
        return exploreService.viewDepartment(id);
    }

    @GetMapping("/investors")
    public List<InvestorDTO> findInvestors(){
        return null;
    }
    @GetMapping
    public List<DepartmentDTO> findDepartments(){
        return null;
    }
}
