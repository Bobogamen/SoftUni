package com.onlineshop.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double subTotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalDiscount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalPrice;

    @OneToOne
    @JoinColumn(nullable = false)
    private OrderStatus status;

    @ManyToOne
    private Address address;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<OrderItem> orderDetails;

    public Order() {
        this.orderDetails = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<OrderItem> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderItem> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderDetails.add(orderItem);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
