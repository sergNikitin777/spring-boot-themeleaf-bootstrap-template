package com.example.web.service;

import com.example.persistance.entity.DeviceGroup;
import com.example.persistance.repository.DeviceGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceGroupServiceImpl implements DeviceGroupService {

    private final DeviceGroupRepository deviceGroupRepository;

    @Override
    public Optional<DeviceGroup> findByAddressId(Integer addressId) {
        return deviceGroupRepository.findByAddressId(addressId);
    }

    @Override
    public List<DeviceGroup> findAll() {
        return deviceGroupRepository.findAll();
    }

    @Override
    public DeviceGroup findById(Integer id) {
        return deviceGroupRepository.findOne(id);
    }
}
