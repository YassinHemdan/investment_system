package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepo extends JpaRepository<Investment, Integer> {
}
