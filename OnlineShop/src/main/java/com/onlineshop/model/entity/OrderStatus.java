package com.onlineshop.model.entity;

import com.onlineshop.model.enums.OrderStatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum name;

    public OrderStatus() {
    }

    public OrderStatus(OrderStatusEnum name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatusEnum getName() {
        return name;
    }

    public void setName(OrderStatusEnum name) {
        this.name = name;
    }
}
