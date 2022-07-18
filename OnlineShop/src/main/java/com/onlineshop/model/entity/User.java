package com.onlineshop.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Address.class, mappedBy = "user")
    private List<Address> address;

    @Column(nullable = false)
    private LocalDateTime registeredOn;

    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = Item.class)
    @JoinTable(name = "cart")
    private List<Item> items;

    @ManyToMany
    private List<UserRole> userRoles;

    public User() {
        this.address = new ArrayList<>();
        this.items = new ArrayList<>();
        this.userRoles = new ArrayList<>();
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addToCart(Item item) {
        this.items.add(item);
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRole(UserRole role) {
        this.userRoles.add(role);
    }

    public void addRole(UserRole role) {
        this.userRoles.add(role);
    }
}
