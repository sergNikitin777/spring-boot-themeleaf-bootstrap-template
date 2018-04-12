package com.example.web.controller;

import com.example.persistance.entity.Device;
import com.example.persistance.entity.DeviceGroup;
import com.example.web.pojo.DevicePojo;
import com.example.web.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @RequestMapping(value = "/admin/devices/{id}", method = RequestMethod.GET)
    public ResponseEntity<Device> devices(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(deviceService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/device/getListByAddress/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> getDeviceListByAddressId(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(deviceService.findAllByAddressId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/device/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addDevice(DevicePojo device) {
        log.debug("addDevice");
        try {
            return new ResponseEntity<>(deviceService.addDevice(device), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/device/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteDevice(@PathVariable("id") Integer id) {
        log.debug("deleteDevice");
        try {
            deviceService.deleteDevice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
