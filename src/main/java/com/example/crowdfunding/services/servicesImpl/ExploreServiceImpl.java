package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.entities.Department;
import com.example.crowdfunding.entities.PriceHistory;
import com.example.crowdfunding.repos.DepartmentRepo;
import com.example.crowdfunding.repos.PriceHistoryRepo;
import com.example.crowdfunding.repos.UserRepo;
import com.example.crowdfunding.services.Iservices.ExploreService;
import com.example.crowdfunding.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ExploreServiceImpl implements ExploreService {
    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;
    private final Converter converter;
    private final PriceHistoryRepo priceHistoryRepo;
    @Override
    public List<InvestorDTO> findInvestors() {
        return converter.toList(userRepo.findInvestors(), InvestorDTO.class);
    }

    @Override
    public List<DepartmentDTO> findDepartments() {
        List<DepartmentDTO> departmentDTOList = converter.toList(departmentRepo.findAll(), DepartmentDTO.class);
        for(DepartmentDTO departmentDTO : departmentDTOList){
            PriceHistory priceHistory = priceHistoryRepo.findLatestPrice(departmentDTO.getId());
            departmentDTO.setPrice(priceHistory.getPrice());
        }

        return departmentDTOList;
    }

    @Override
    public ViewDepartmentDTO viewDepartment(int id) {
        ViewDepartmentDTO viewDepartmentDTO = departmentRepo.viewDepartment(id);
        viewDepartmentDTO.setPriceHistoryDTOList(converter.toList(priceHistoryRepo.findApartmentHistory(id), PriceHistoryDTO.class));

        return viewDepartmentDTO;
    }
    public String deleteApartment(int id){
        departmentRepo.deleteById(id);
        return "success";
    }

    public void saveApartment(AddDepartmentDTO addDepartmentDTO) {
        Department department = converter.convert(addDepartmentDTO, Department.class);
        department = departmentRepo.save(department);

        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setPrice(addDepartmentDTO.getPrice());
        System.out.println(priceHistory.getPrice());
        priceHistory.setDepartment(department);
        priceHistory.setTime(LocalTime.now());
        priceHistory.setDate(LocalDate.now());

        priceHistoryRepo.save(priceHistory);
    }
    public List<UserDTO> findAll(){
        return converter.toList(userRepo.findAll(), UserDTO.class);
    }
    public String deleteUser(int id){
        userRepo.deleteById(id);
        return "user deleted successfully";
    }
}
