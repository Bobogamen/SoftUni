package com.onlineshop.repository;

import com.onlineshop.model.entity.Order;
import com.onlineshop.model.entity.OrderItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUserId(long user_id);

    Order getOrderById(long id);
}
