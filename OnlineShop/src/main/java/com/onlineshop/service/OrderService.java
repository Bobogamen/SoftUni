package com.onlineshop.service;

import com.onlineshop.model.dto.OrderDTO;
import com.onlineshop.model.entity.*;
import com.onlineshop.model.enums.OrderStatusEnum;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderStatusRepository orderStatusRepository,
                        UserRepository userRepository,
                        AddressRepository addressRepository,
                        ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
    }

    public void newOrder(OrderDTO orderDTO, ShopUserDetails user) {

        UserEntity userById = this.userRepository.getUserById(user.getId());

        String addressLine = orderDTO.getAddressLine().split("town:")[0];
        Address addressByAddressLine = this.addressRepository.findAddressByAddressLine(addressLine);

        Collection<Item> cartItems = user.getCart();

        OrderStatus orderStatus = this.orderStatusRepository.getOrderStatusByName(OrderStatusEnum.PENDING);

        //Order set up
        Order order = new Order();

        for (Item item : cartItems) {
            OrderItem orderItem = new OrderItem(order, item, item.getPrice(), item.getDiscount());
            order.addOrderItem(orderItem);
        }

        if (user.getTotalDiscountValue() > 0) {
            order.setSubTotal(user.getTotalPrice());
        }

        order.setTotalDiscount(user.getTotalDiscountValue());
        order.setTotalPrice(user.getTotalPrice() - user.getTotalDiscountValue());
        order.setAddress(addressByAddressLine);
        order.setUser(userById);
        order.setStatus(orderStatus);


        //Item timesOrdered set up
        cartItems.forEach(i -> {
            int timesOrdered = i.getTimesOrdered();
            timesOrdered++;
            i.setTimesOrdered(timesOrdered);
        });

        //User set up
        double userCurrentOrderSum = userById.getOrdersSum();
        userById.setOrdersSum(userCurrentOrderSum + order.getTotalPrice());
        int userCurrentOrderCount = userById.getOrdersCount();
        userCurrentOrderCount++;
        userById.setOrdersCount(userCurrentOrderCount);
        //--------------------------------------------------

        this.orderRepository.save(order);

        this.itemRepository.saveAll(cartItems);

        this.userRepository.save(userById);
    }

    public void changeOrderStatus(long id, String status) {

        Order orderById = this.orderRepository.getOrderById(id);

        List<OrderStatusEnum> newStatus = Arrays.stream(OrderStatusEnum.values()).
                filter(o -> o.name().equals(status)).toList();

        OrderStatus orderStatus = this.orderStatusRepository.getOrderStatusByName(newStatus.get(0));

        orderById.setStatus(orderStatus);

        this.orderRepository.save(orderById);
    }

    public List<Order> getAllOrdersByUserEntityId(long id) {
        return this.orderRepository.getAllByUserId(id);
    }

    public Order getOrderById(long id) {
        return this.orderRepository.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll().stream().sorted(Comparator.comparingLong(x -> x.getStatus().getId())).toList();
    }
}
