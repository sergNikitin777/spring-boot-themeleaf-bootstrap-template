package com.example.persistance.repository;

import com.example.persistance.entity.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroup, Integer> {

}
