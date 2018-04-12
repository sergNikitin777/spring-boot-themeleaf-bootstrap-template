package com.example.web.controller;

import com.example.persistance.entity.Device;
import com.example.persistance.entity.DeviceGroup;
import com.example.web.service.DeviceGroupService;
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
public class DeviceGroupController {

    private final DeviceGroupService deviceGroupService;

    @RequestMapping(value = "/admin/deviceGroupList", method = RequestMethod.GET)
    public ResponseEntity<List<DeviceGroup>> getDeviceGroupList() {
        return new ResponseEntity<>(deviceGroupService.findAll(), HttpStatus.OK);

    }

    @RequestMapping(value = "/admin/deviceGroup/{id}", method = RequestMethod.GET)
    public ResponseEntity<DeviceGroup> getDeviceGroup(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(deviceGroupService.findById(id), HttpStatus.OK);
    }

}
