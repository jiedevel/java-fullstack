package com.example.fullstack41.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fullstack41.dto.UsersDTO;

@Repository
public interface UserJpaRepository extends JpaRepository<UsersDTO, Long> {
        UsersDTO findByName(String name);
}
