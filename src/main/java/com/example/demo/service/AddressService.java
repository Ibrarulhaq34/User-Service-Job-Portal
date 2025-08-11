package com.example.demo.service;

import com.example.demo.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AddressService {
    Address createAddress(Long userId, Address address);
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long id);
    Address updateAddress(Long id, Address address);
    void deleteAddress(Long id);
}
