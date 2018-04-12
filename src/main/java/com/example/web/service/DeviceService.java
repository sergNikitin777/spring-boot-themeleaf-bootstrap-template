package com.example.web.service;


import com.example.persistance.entity.Device;
import com.example.web.pojo.DevicePojo;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<Device> findAll();

    Device findById(Integer id);

    List<Device> findAllByAddressId(Integer id);

    Integer addDevice(Device device);

    void updateDevice(Device device);

    void deleteDevice(Integer id);

    Integer addDevice(DevicePojo devicePojo);
}
