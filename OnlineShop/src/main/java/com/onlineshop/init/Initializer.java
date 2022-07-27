package com.onlineshop.init;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.CategoryEnum;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    private final PictureService pictureService;

    @Autowired //not need it, but I put it in purpose, to remind me.
    public Initializer(RoleRepository roleRepository, CategoryRepository categoryRepository, PictureService pictureService) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.pictureService = pictureService;
    }

    @Override
    public void run(String... args) throws Exception {

        //CATEGORY CREATION
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values()).map(Category::new).toList();

            this.categoryRepository.saveAll(categories);
        }

        if (this.roleRepository.count() == 0) {
            List<Role> roles = Arrays.stream(RoleEnum.values()).map(Role::new).toList();

            this.roleRepository.saveAll(roles);
        }

        //Directory "pictures" CREATION

        this.pictureService.init();

    }
}
