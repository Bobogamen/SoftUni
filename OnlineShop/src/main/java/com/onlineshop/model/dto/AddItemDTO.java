package com.onlineshop.model.dto;

import javax.validation.constraints.*;

public class AddItemDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Name must at least 2 character")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Positive(message = "Price must be positive number")
    @Digits(integer = 10, fraction = 2, message = "Price is more than 10 digits")
    private double price;

    @NotNull(message = "Enter 0 if discount is not applied")
    @PositiveOrZero
    private int discount;

    @NotNull(message = "Item must be in category")
    private String category;

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
