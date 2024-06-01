package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.AddDepartmentDTO;
import com.example.crowdfunding.dtos.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.crowdfunding.utils.ApiUri.ADMIN_URI;

@RestController
@AllArgsConstructor
@RequestMapping(ADMIN_URI)
public class AdminController {
    @PostMapping("/add-admin")
    public RegisterDTO saveAdmin(){
        return null;
    }
    @PostMapping("/add-apartment")
    public AddDepartmentDTO saveApartment(){
        return null;
    }
    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable(name = "id") int id){
        return new ResponseEntity<>("admin has been removed successfully", HttpStatus.OK);
    }
    @PostMapping("/delete-apartment/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable(name = "id") int id){
        return new ResponseEntity<>("apartment has been removed successfully", HttpStatus.OK);
    }
}
