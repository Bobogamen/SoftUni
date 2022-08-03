package com.onlineshop.model.entity;

import com.onlineshop.model.enums.CategoryEnum;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Enumerated(EnumType.STRING)
    private String name;

//    public Category(CategoryEnum categoryEnum) {
//        this.name = categoryEnum;
//    }

    public Category() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public CategoryEnum getName() {
//          return name;
//    }
//
//    public void setName(CategoryEnum name) {
//        this.name = name;
//    }
}

