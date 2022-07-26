package com.onlineshop.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime registeredOn;

    private int ordersCount;

    @Column(columnDefinition="DECIMAL(10,2)")
    private double ordersSum;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Address.class, mappedBy = "userEntity")
    private List<Address> address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public UserEntity() {
        this.address = new ArrayList<>();
        this.roles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void addAddress(Address address) {
        this.address.add(address);
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getUserRoles() {
        return roles;
    }

    public void setUserRole(Role role) {
        this.roles.add(role);
    }

    public void addRole(Role role) {
        this.roles.add(role);
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
