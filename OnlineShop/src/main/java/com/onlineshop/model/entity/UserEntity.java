package com.onlineshop.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @OneToMany(targetEntity = Address.class, mappedBy = "userEntity")
    private Set<Address> address;

    @Column(nullable = false)
    private LocalDateTime registeredOn;

    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = Item.class)
    @JoinTable(name = "cart")
    private Set<Item> items;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public UserEntity() {
        this.address = new HashSet<>();
        this.items = new HashSet<>();
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

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addToCart(Item item) {
        this.items.add(item);
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
}
