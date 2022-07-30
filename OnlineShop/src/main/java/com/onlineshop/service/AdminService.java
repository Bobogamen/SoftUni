package com.onlineshop.service;

import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
