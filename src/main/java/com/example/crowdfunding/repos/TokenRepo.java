package com.example.crowdfunding.repos;

import com.example.crowdfunding.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Integer> {
}
