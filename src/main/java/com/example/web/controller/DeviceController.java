package com.example.web.controller;

import com.example.persistance.entity.Device;
import com.example.web.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @RequestMapping("/admin/device")
    public String getUserList(Model model)
    {
        log.debug("getDeviceList");
        model.addAttribute(deviceService.findAll());

        return "deviceList";
    }

    @RequestMapping(value = "/admin/devices", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> devices(@PathParam("id") Integer id) {
        //List<Device> devices =deviceService.findAll();
        List<Device> devices = new ArrayList<>();
        Device d1 = new Device(1,"Пермь",85.3323F,87.2323F,1);
        devices.add(d1);
        Device d2 = new Device(2,"Пермь 2",85.3323F,87.2323F,2);
        devices.add(d2);
        return new ResponseEntity<List<Device>>(devices, HttpStatus.OK);
    }
}
