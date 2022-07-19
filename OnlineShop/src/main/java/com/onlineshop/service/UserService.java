package com.onlineshop.service;

import com.onlineshop.model.dto.RegistrationDTO;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.UserRepository;
import com.onlineshop.repository.RoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void register(RegistrationDTO registrationDTO) {

        Role role = this.roleRepository.getByName(RoleEnum.CLIENT);

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setEmail(registrationDTO.getEmail());
        newUserEntity.setName(registrationDTO.getName());
        newUserEntity.setRegisteredOn(LocalDateTime.now());
        newUserEntity.setUserRole(role);
        newUserEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        this.userRepository.save(newUserEntity);

    }

    public void login(UserEntity userEntity) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    };
}
