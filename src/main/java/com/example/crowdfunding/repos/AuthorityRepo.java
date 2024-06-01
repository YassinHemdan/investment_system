package com.example.crowdfunding.repos;

import com.example.crowdfunding.entities.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
    Optional<Authority> findByAuthorityName(String name);
}