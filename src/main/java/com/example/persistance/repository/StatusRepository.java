package com.example.persistance.repository;

import com.example.persistance.entity.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository  extends JpaRepository<DeviceStatus, Integer> {
}
