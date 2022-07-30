package com.onlineshop;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "items", schema = "onlineshop", catalog = "")
public class ItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "discount")
    private int discount;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "times_ordered")
    private int timesOrdered;
    @Basic
    @Column(name = "category_id")
    private Long categoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsEntity that = (ItemsEntity) o;
        return id == that.id && discount == that.discount && timesOrdered == that.timesOrdered && Objects.equals(description, that.description) && Objects.equals(name, that.name) && Objects.equals(picture, that.picture) && Objects.equals(price, that.price) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, discount, name, picture, price, timesOrdered, categoryId);
    }
}
