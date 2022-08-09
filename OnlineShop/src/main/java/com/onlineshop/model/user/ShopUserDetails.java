package com.onlineshop.model.user;

import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.Item;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private final Collection<Item> cart;


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
        this.cart = new ArrayList<>();
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

    public Collection<Item> getCart() {
        return cart;
    }

    public double getDiscountValue(int price, int discount) {
        return price * 1.0 * discount / 100;
    }

    public double getTotalPrice() {
        return this.cart.stream().mapToDouble(Item::getPrice).sum();
    }

    public double getTotalDiscountValue() {
        return this.cart.stream().
                filter(i -> i.getDiscount() > 0).
                mapToDouble(x -> x.getPrice() * x.getDiscount() / 100).
                sum();
    }

    public void removeCartItem(long id) {
        for (Item item : this.cart) {
            if (item.getId() == id) {
                this.cart.remove(item);
                return;
            }
        }
    }

    public void addItem(Item item) {
        this.cart.add(item);
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
