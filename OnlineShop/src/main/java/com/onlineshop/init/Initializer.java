package com.onlineshop.init;

import com.onlineshop.model.entity.OrderStatus;
import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.OrderStatusEnum;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.OrderStatusRepository;
import com.onlineshop.repository.RoleRepository;
import com.onlineshop.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PictureService pictureService;

    private final OrderStatusRepository orderStatusRepository;

    @Autowired //not need it, but I put it in purpose, to remind me.
    public Initializer(RoleRepository roleRepository,
                       PictureService pictureService,
                       OrderStatusRepository orderStatusRepository) {
        this.roleRepository = roleRepository;
        this.pictureService = pictureService;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //ROLES CREATION
        if (this.roleRepository.count() == 0) {
            List<Role> roles = Arrays.stream(RoleEnum.values()).map(Role::new).toList();

            this.roleRepository.saveAll(roles);
        }

        //ORDER_STATUS CREATION
        if (this.orderStatusRepository.count() == 0) {
            List<OrderStatus> orderStatuses = Arrays.stream(OrderStatusEnum.values()).map(OrderStatus::new).toList();

            this.orderStatusRepository.saveAll(orderStatuses);
        }

        //Directory "pictures" CREATION

        this.pictureService.init();

    }
}
