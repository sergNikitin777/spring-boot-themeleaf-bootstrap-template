package com.example.persistance.repository;

import com.example.persistance.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findAllByAddressId(Integer id);
}
