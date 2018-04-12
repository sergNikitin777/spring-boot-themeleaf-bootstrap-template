package com.example.web.service;

import com.example.persistance.entity.DeviceGroup;

import java.util.List;
import java.util.Optional;

public interface DeviceGroupService {

    Optional<DeviceGroup> findByAddressId(Integer addressId);

    List<DeviceGroup> findAll();

    DeviceGroup findById(Integer id);
}
