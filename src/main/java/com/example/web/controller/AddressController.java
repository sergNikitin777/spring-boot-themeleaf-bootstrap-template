package com.example.web.controller;

import com.example.persistance.entity.Address;
import com.example.persistance.entity.Device;
import com.example.web.service.AddressService;
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
public class AddressController {

    private final AddressService addressService;

    @RequestMapping("/admin/address")
    public String getAddressList(Model model)
    {
        log.debug("getAddressList");
        model.addAttribute(addressService.findAll());

        return "addressList";
    }

    @RequestMapping(value = "/admin/address", method = RequestMethod.GET)
    public ResponseEntity<List<Address>> addresses(@PathParam("id") Integer id) {
        //List<Device> devices =deviceService.findAll();
        List<Address> addresses = new ArrayList<>();
        Address d1 = new Address(1,"Пермь",85.3323F,87.2323F);
        addresses.add(d1);
        Address d2 = new Address(2,"Пермь 2",85.3323F,87.2323F);
        addresses.add(d2);
        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }
}
