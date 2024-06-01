package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.dtos.DepartmentDTO;
import com.example.crowdfunding.dtos.InvestorDTO;
import com.example.crowdfunding.dtos.ViewDepartmentDTO;
import com.example.crowdfunding.repos.DepartmentRepo;
import com.example.crowdfunding.repos.UserRepo;
import com.example.crowdfunding.services.Iservices.ExploreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExploreServiceImpl implements ExploreService {
    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;

    @Override
    public List<InvestorDTO> findInvestors() {
        return List.of();
    }

    @Override
    public List<DepartmentDTO> findDepartments() {
        return List.of();
    }

    @Override
    public ViewDepartmentDTO viewDepartment(int id) {
        return departmentRepo.viewDepartment(id);
    }
}
