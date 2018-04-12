package com.example.web.service;

import com.example.persistance.entity.Device;
import com.example.persistance.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    @Override
    public List<Device> findAll() {
        return Arrays.asList(new Device());
        //return deviceRepository.findAll();
    }
}
