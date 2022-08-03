package com.onlineshop.service;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.enums.CategoryEnum;
import com.onlineshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public void addCategory(String name) {
        Category category = new Category();

        category.setName(name.toUpperCase().replace(' ', '_'));

        this.categoryRepository.save(category);
    }


}
