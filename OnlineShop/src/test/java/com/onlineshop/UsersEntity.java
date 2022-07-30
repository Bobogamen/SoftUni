package com.onlineshop;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "onlineshop", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "orders_count")
    private int ordersCount;
    @Basic
    @Column(name = "orders_sum")
    private BigDecimal ordersSum;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "registered_on")
    private Timestamp registeredOn;

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

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public BigDecimal getOrdersSum() {
        return ordersSum;
    }

    public void setOrdersSum(BigDecimal ordersSum) {
        this.ordersSum = ordersSum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Timestamp registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id && ordersCount == that.ordersCount && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(ordersSum, that.ordersSum) && Objects.equals(password, that.password) && Objects.equals(registeredOn, that.registeredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, ordersCount, ordersSum, password, registeredOn);
    }
}
