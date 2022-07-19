package com.onlineshop.init;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.CategoryEnum;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.CategoryRepository;
import com.onlineshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired //not need it, but I put it in purpose, to remind me.
    public Seeder(RoleRepository roleRepository, CategoryRepository categoryRepository) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values()).map(Category::new).toList();

            this.categoryRepository.saveAll(categories);
        }

        if (this.roleRepository.count() == 0) {
            List<Role> roles = Arrays.stream(RoleEnum.values()).map(Role::new).toList();

            this.roleRepository.saveAll(roles);
        }
    }
}
