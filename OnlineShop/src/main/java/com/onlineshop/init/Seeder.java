package com.onlineshop.init;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.entity.UserRole;
import com.onlineshop.model.enums.CategoryEnum;
import com.onlineshop.model.enums.UserRoleEnum;
import com.onlineshop.repository.CategoryRepository;
import com.onlineshop.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired //not need it, but I put it in purpose, to remind me.
    public Seeder(UserRoleRepository userRoleRepository, CategoryRepository categoryRepository) {
        this.userRoleRepository = userRoleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values()).map(Category::new).toList();

            this.categoryRepository.saveAll(categories);
        }

        if (this.userRoleRepository.count() == 0) {
            List<UserRole> roles = Arrays.stream(UserRoleEnum.values()).map(UserRole::new).toList();

            this.userRoleRepository.saveAll(roles);
        }
    }
}
