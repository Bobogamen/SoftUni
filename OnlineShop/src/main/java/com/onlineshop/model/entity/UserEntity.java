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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime registeredOn;

    private int ordersCount;

    @Column(columnDefinition="DECIMAL(10,2)")
    private double ordersSum;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity")
    private Set<Address> address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public UserEntity() {
        this.address = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getRolesNames() {
        return String.join("/", roles.stream().map(role -> role.getName().name()).toList());
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
