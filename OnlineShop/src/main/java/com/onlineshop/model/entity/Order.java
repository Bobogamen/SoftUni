package com.onlineshop.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_items",
                joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "items_id", referencedColumnName = "id"))
    private Set<Item> items;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double subTotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalDiscount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalPrice;

    @ManyToOne
    private Address address;

    @OneToOne
    private UserEntity user;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
