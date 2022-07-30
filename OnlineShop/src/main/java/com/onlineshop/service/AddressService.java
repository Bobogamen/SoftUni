package com.onlineshop.service;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.repository.AddressRepository;
import com.onlineshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    public void addAddress(AddAddressDTO addAddressDTO, UserDetails user) {

        Optional<UserEntity> userOpt = userRepository.findUserByEmail(user.getUsername());

        Address address = new Address();
        address.setName(addAddressDTO.getName());
        address.setAddressLine(addAddressDTO.getAddressLine());
        address.setTown(addAddressDTO.getTown());
        address.setUser(userOpt.get());

        this.addressRepository.save(address);
    }

    public List<Address> getAddressesById(long id) {
        return this.addressRepository.getAddressesByUserEntityId(id);
    }

    public void editAddressByUser(ShopUserDetails user) {
        return this.addressRepository.editAdd
    }
}
