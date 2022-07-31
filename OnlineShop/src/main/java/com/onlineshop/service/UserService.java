package com.onlineshop.service;

import com.onlineshop.model.dto.RegistrationDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationDTO registrationDTO) {

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setEmail(registrationDTO.getEmail());
        newUserEntity.setName(registrationDTO.getName());
        newUserEntity.setRegisteredOn(LocalDateTime.now());
        newUserEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        this.userRepository.save(newUserEntity);

    }

    public void editNameByUserId(long id, String name) {
        UserEntity userById = this.userRepository.getUserById(id);
        userById.setName(name);

        this.userRepository.save(userById);
    }

    public String getNameByUserEntityId(long id) {
        return this.userRepository.getNameByUserEntityId(id);
    }


//    public void login(UserEntity userEntity) {
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());
//
//        Authentication auth =
//                new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        userDetails.getPassword(),
//                        userDetails.getAuthorities());
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    };
}
