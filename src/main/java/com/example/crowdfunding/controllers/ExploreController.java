package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.DepartmentDTO;
import com.example.crowdfunding.dtos.OtherProfileDTO;
import com.example.crowdfunding.dtos.InvestorDTO;
import com.example.crowdfunding.dtos.ViewDepartmentDTO;

import com.example.crowdfunding.services.servicesImpl.ExploreServiceImpl;
import com.example.crowdfunding.services.servicesImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.crowdfunding.utils.ApiUri.EXPLORE_URI;
import java.util.List;

@RequestMapping(EXPLORE_URI)
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExploreController {
    private final UserServiceImpl userService;
    private final ExploreServiceImpl exploreService;
    @GetMapping("/{username}/portfolio")
    public OtherProfileDTO viewUserPortfolio(@PathVariable(name = "username") String username) throws Exception {
        return userService.otherProfile(username);
    }

    @GetMapping("/department/{id}")
    public ViewDepartmentDTO viewDepartment(@PathVariable(name = "id") int id){
        return exploreService.viewDepartment(id);
    }

    @GetMapping("/investors")
    public List<InvestorDTO> findInvestors(){
        return exploreService.findInvestors();
    }

    @GetMapping
    public List<DepartmentDTO> findDepartments(){
        System.out.println("accessed");
        return exploreService.findDepartments();
    }
}
