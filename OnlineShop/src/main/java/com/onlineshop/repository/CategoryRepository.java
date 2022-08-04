package com.onlineshop.repository;

import com.onlineshop.model.entity.Category;
import com.onlineshop.model.enums.CategoryEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getByName(String name);
}
