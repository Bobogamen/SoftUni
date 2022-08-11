package com.onlineshop.repository;

import com.onlineshop.model.entity.OrderStatus;
import com.onlineshop.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus getOrderStatusByName(OrderStatusEnum name);
}
