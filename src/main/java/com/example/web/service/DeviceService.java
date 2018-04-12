package com.example.web.service;

import com.example.persistance.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();
}
