package com.example.persistance.repository;

import com.example.persistance.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Integer> {
}
