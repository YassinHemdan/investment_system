package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.services.servicesImpl.ExploreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.crowdfunding.utils.ApiUri.ADMIN_URI;

@RestController
@AllArgsConstructor
@RequestMapping(ADMIN_URI)
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final ExploreServiceImpl exploreService;
    @PostMapping("/add-user")
    public RegisterDTO saveAdmin(){
        return null;
    }
    @PostMapping("/add-apartment")
    public TestResponseDTO saveApartment(@RequestBody AddDepartmentDTO addDepartmentDTO){
        exploreService.saveApartment(addDepartmentDTO);
        return new TestResponseDTO("Apartment Added Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete-user/{id}")
    public TestResponseDTO deleteAdmin(@PathVariable(name = "id") int id){
        exploreService.deleteUser(id);
        return new TestResponseDTO("admin has been removed successfully", HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserDTO> findAll(){
        return exploreService.findAll();
    }
    @DeleteMapping("/delete-apartment/{id}")
    public TestResponseDTO deleteApartment(@PathVariable(name = "id") int id){
        String res = exploreService.deleteApartment(id);
        return new TestResponseDTO("deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/apartments")
    public List<DepartmentDTO> findAllDepartments(){
        return exploreService.findDepartments();
    }
}
