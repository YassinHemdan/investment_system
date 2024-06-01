package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.dtos.DepartmentDTO;
import com.example.crowdfunding.dtos.InvestorDTO;
import com.example.crowdfunding.dtos.ViewDepartmentDTO;

import java.util.List;

public interface ExploreService {
    List<InvestorDTO> findInvestors();
    List<DepartmentDTO> findDepartments();
    ViewDepartmentDTO viewDepartment(int id);

}
