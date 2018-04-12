package com.example.web.controller;

import com.example.web.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @RequestMapping("/admin/device")
    public String getDeviceList(Model model)
    {
        log.debug("getDeviceList");
        model.addAttribute(deviceService.findAll());

        return "deviceList";
    }
}
