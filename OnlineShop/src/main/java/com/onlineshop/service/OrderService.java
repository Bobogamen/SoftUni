package com.onlineshop.service;

import com.onlineshop.model.dto.OrderDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.Order;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.repository.AddressRepository;
import com.onlineshop.repository.OrderRepository;
import com.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public void newOrder(OrderDTO orderDTO, ShopUserDetails user) {

        Order order = new Order();

        UserEntity userById = this.userRepository.getUserById(user.getId());
        String addressLine = orderDTO.getAddressLine().split("town:")[0];
        Address addressByAddressLine = this.addressRepository.findAddressByAddressLine(addressLine);

        order.setItems(new HashSet<>(user.getCart()));

        if (user.getTotalDiscountValue() > 0) {
            order.setSubTotal(user.getTotalPrice());
        }

        order.setTotalDiscount(user.getTotalDiscountValue());
        order.setTotalPrice(user.getTotalPrice() - user.getTotalDiscountValue());
        order.setAddress(addressByAddressLine);
        order.setUser(userById);

        this.orderRepository.save(order);

        double userCurrentOrderSum = userById.getOrdersSum();
        userById.setOrdersSum(userCurrentOrderSum + order.getTotalPrice());
        int userCurrentOrderCount = userById.getOrdersCount();
        userCurrentOrderCount++;
        userById.setOrdersCount(userCurrentOrderCount);

        this.userRepository.save(userById);
    }

    public List<Order> getAllOrdersByUserEntityId(long id) {
        return this.orderRepository.getAllByUserId(id);
    }

    public Order getOrderById(long id) {
        return this.orderRepository.getOrderById(id);
    }
}
