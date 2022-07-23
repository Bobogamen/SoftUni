package com.onlineshop.service;

import com.onlineshop.model.entity.Role;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findUserByEmail(username).map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
    }

    private UserDetails map(UserEntity userEntity) {

        return new ShopUserDetails(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getRegisteredOn(),
                userEntity.getAddress(),
                userEntity.getUserRoles().stream().map(this::map).toList());

    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());
    }
}
