package com.onlineshop.model.user;

import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class ShopUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final String name;
    private final LocalDateTime registeredOn;
    private final Collection<Address> addresses;
    private final Collection<GrantedAuthority> authorities;

    public ShopUserDetails(String username,
                           String password,
                           String name,
                           LocalDateTime registeredOn,
                           Collection<Address> addresses,
                           Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.registeredOn = registeredOn;
        this.addresses = addresses;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
