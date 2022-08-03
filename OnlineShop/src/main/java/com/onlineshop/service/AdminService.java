package com.onlineshop.service;

import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    public AdminService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(long id) {
        UserEntity userById = userRepository.getUserById(id);

        userById.getAddress().forEach(a -> addressService.deleteAddressById(a.getId()));

        this.userRepository.deleteById(id);
    }
}
