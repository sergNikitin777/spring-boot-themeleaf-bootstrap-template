package com.example.web.controller;

import com.example.persistance.entity.Device;
import com.example.web.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @RequestMapping(value = "/admin/devices", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> getDeviceList() {
        log.debug("getDeviceList");
        return new ResponseEntity<>(deviceService.findAll(), HttpStatus.OK);

    }

    @RequestMapping(value = "/admin/device/{id}", method = RequestMethod.GET)
    public ResponseEntity<Device> devices(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(deviceService.findById(id), HttpStatus.OK);
    }
}
