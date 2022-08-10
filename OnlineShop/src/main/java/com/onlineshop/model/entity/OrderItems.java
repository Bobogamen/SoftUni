package com.onlineshop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders_items")
public class OrderItems {

    private long id;
    private Order order;
    private Item item;

    private double price;
    private double discount;


}
