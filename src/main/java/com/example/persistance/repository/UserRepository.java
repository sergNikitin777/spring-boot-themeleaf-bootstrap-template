package com.example.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.persistance.entity.auth.Users;

public interface UserRepository extends JpaRepository<Users, Integer>
{
    Users findUserByUsername(String username);
    
    Users findUserByEmail(String email);

    Users findUserByUsernameAndPassword(String username, String givenPassword);
    
}
