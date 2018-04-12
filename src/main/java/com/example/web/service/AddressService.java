package com.example.web.service;

import com.example.persistance.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
}
