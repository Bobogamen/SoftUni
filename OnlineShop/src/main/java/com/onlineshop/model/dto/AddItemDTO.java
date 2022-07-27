package com.onlineshop.model.dto;

import com.onlineshop.model.enums.CategoryEnum;

import javax.validation.constraints.*;

public class AddItemDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must at least 3 character")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Positive(message = "Price must be positive number")
    @Digits(integer = 10, fraction = 2, message = "Price is more than 10 digits")
    private double price;

    @NotNull(message = "Enter 0 if discount is not applied")
    @PositiveOrZero
    private double discount;

    @NotNull(message = "Item must be in category")
    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
