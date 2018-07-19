package com.example.web.service;

import com.example.persistance.entity.DeviceGroup;

import java.util.List;

public interface DeviceGroupService {

    List<DeviceGroup> findAll();

    DeviceGroup findById(Integer id);
}
