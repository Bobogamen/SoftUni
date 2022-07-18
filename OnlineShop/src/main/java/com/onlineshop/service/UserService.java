package com.onlineshop.service;

import com.onlineshop.model.dto.RegistrationDTO;
import com.onlineshop.model.entity.User;
import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.UserRepository;
import com.onlineshop.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationDTO registrationDTO) {

        Role role = this.userRoleRepository.getByName(RoleEnum.CLIENT);

        User newUser = new User();
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setName(registrationDTO.getName());
        newUser.setRegisteredOn(LocalDateTime.now());
        newUser.setUserRole(role);
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        this.userRepository.save(newUser);

    }
}
