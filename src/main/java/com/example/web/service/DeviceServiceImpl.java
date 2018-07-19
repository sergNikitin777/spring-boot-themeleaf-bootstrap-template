package com.example.web.service;

import com.example.persistance.entity.Address;
import com.example.persistance.entity.Device;
import com.example.persistance.entity.DeviceGroup;
import com.example.persistance.entity.DeviceStatus;
import com.example.persistance.repository.AddressRepository;
import com.example.persistance.repository.DeviceGroupRepository;
import com.example.persistance.repository.DeviceRepository;
import com.example.persistance.repository.StatusRepository;
import com.example.web.pojo.DevicePojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final StatusRepository statusRepository;
    private final DeviceGroupRepository deviceGroupRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findById(Integer id) {
        return deviceRepository.findOne(id);
    }

    @Override
    public List<Device> findAllByAddressId(Integer id) {
        return deviceRepository.findAllByAddressId(id);
    }

    @Override
    public Integer addDevice(Device device) {
        return deviceRepository.save(device).getId();
    }

    @Override
    public void updateDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Integer id) {
        deviceRepository.delete(id);
    }

    @Override
    public Integer addDevice(DevicePojo devicePojo) {
        DeviceStatus status = statusRepository.findOne(devicePojo.getStatusId());
        DeviceGroup deviceGroup = deviceGroupRepository.findOne(devicePojo.getDeviceGroupId());
        Address address = addressRepository.findOne(devicePojo.getAddressId());
        return deviceRepository.save(new Device(deviceGroup, address, devicePojo.getName(), devicePojo.getDescription(), devicePojo.getMark(), devicePojo.getModel(), devicePojo.getHeightAccess(), devicePojo.getElectricityAccess(), status)).getId();
    }
}
