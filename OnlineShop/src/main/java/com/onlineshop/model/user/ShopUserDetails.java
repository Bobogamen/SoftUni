package com.onlineshop.model.user;

import com.onlineshop.model.entity.Address;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class ShopUserDetails implements UserDetails {

    private final long id;
    private final String username;
    private final String password;
    private final String name;
    private int ordersCount;
    private double ordersSum;
    private final LocalDateTime registeredOn;
    private final Collection<Address> addresses;
    private final Collection<GrantedAuthority> authorities;

    public ShopUserDetails(long id, String username,
                           String password,
                           String name,
                           int ordersCount, double ordersSum, LocalDateTime registeredOn,
                           Collection<Address> addresses,
                           Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.ordersCount = ordersCount;
        this.ordersSum = ordersSum;
        this.registeredOn = registeredOn;
        this.addresses = addresses;
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    public String getRegisteredDate() {
        return String.format("%d - %s - %d", this.registeredOn.getDayOfMonth(), this.registeredOn.getMonth().toString(), this.registeredOn.getYear());
    }
    public String getRegisteredTime() {
        return String.format("%d:%d", this.registeredOn.getHour(), this.registeredOn.getMinute());
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

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public double getOrdersSum() {
        return ordersSum;
    }

    public void setOrdersSum(double ordersSum) {
        this.ordersSum = ordersSum;
    }
}
