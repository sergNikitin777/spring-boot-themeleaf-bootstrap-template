package com.example.persistance.repository;

import com.example.persistance.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>
{

}
