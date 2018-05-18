package com.example.persistance.repository;

import com.example.persistance.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Integer> {
}
