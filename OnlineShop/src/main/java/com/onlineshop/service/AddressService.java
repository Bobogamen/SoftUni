package com.onlineshop.service;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.repository.AddressRepository;
import com.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    public void addAddress(AddAddressDTO addAddressDTO, long profileId) {

        UserEntity userById = userRepository.getUserById(profileId);

        Address address = new Address();
        address.setName(addAddressDTO.getName());
        address.setDescription(addAddressDTO.getDescription());
        address.setUser(userById);

        this.addressRepository.save(address);
    }
}
