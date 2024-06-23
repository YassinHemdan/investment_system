package com.example.crowdfunding.repos;

import com.example.crowdfunding.dtos.AvailableAreaDTO;
import com.example.crowdfunding.dtos.ViewDepartmentDTO;
import com.example.crowdfunding.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    @Query(nativeQuery = true)
    ViewDepartmentDTO viewDepartment(@Param("department_id") int department_id);

    @Query(nativeQuery = true)
    AvailableAreaDTO getAvailableArea(@Param("department_id") int department_id);
}
