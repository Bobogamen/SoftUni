package com.onlineshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders_items", schema = "onlineshop", catalog = "")
public class OrdersItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "order_id")
    private long orderId;
    @Basic
    @Column(name = "items_id")
    private long itemsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getItemsId() {
        return itemsId;
    }

    public void setItemsId(long itemsId) {
        this.itemsId = itemsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersItemsEntity that = (OrdersItemsEntity) o;
        return orderId == that.orderId && itemsId == that.itemsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemsId);
    }
}
