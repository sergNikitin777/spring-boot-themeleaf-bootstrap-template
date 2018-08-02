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
    public Device updateDevice(Device device) {

        Device temp = new Device();
        if (device.getId()!=null){
            temp.setId(device.getId());
        }
        if (device.getName()!=null){
            temp.setName(device.getName());
        }
        if (device.getDescripion()!=null){
            temp.setDescripion(device.getDescripion());
        }
        if (device.getAddress()!=null){
            if (device.getAddress().getId()!=null){
                Address address = addressRepository.getOne(device.getAddress().getId());
                temp.setAddress(address);
            }
        }
        if (device.getEkt()!=null){
            if (device.getEkt().getId()!=null){
                Ekt ekt = ektRepository.getOne(device.getEkt().getId());
                temp.setEkt(ekt);
            }
        }
        if (device.getManufacturer()!=null){
            temp.setManufacturer(device.getManufacturer());
        }
        if (device.getModel()!=null){
            temp.setModel(device.getModel());
        }
        if (device.getProductionYear()!=null){
            temp.setProductionYear(device.getProductionYear());
        }
        if (device.getAccessNeeded()!=null){
            temp.setAccessNeeded(device.getAccessNeeded());
        }
        if (device.getMaintence()!=null){
            temp.setMaintence(device.getMaintence());
        }
        if (device.getDeviceGroup()!=null){
            if (device.getDeviceGroup().getId()!=null){
                DeviceGroup deviceGroup = deviceGroupRepository.getOne(device.getDeviceGroup().getId());
                temp.setDeviceGroup(deviceGroup);
            }
        }
        if (device.getElectricityAccess()!=null){
            temp.setElectricityAccess(device.getElectricityAccess());
        }
        if (device.getHeightAccess()!=null){
            temp.setHeightAccess(device.getHeightAccess());
        }
        return deviceRepository.save(temp);
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
