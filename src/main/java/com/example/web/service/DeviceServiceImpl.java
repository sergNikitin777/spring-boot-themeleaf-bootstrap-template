package com.example.web.service;

import com.example.persistance.entity.*;
import com.example.persistance.repository.*;
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
    private final EktRepository ektRepository;

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
        Ekt ekt = ektRepository.findOne(devicePojo.getEktId());
        return deviceRepository.save(new Device(deviceGroup, address, devicePojo.getName(), devicePojo.getDescription(), devicePojo.getModel(), status, devicePojo.getHeightAccess(), devicePojo.getElectricityAccess(), ekt, devicePojo.getManufacturer(),devicePojo.getProductionYear(), devicePojo.getAccessNeeded(), devicePojo.getMaintence())).getId();
    }
}
