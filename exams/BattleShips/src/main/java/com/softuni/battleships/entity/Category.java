package com.softuni.battleships.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private ShipType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(ShipType name) {
        this.name = name;
    }

    public Category() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShipType getName() {
        return name;
    }

    public void setName(ShipType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
