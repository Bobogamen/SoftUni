package com.onlineshop.repository;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getByName(CategoryEnum name);
}
