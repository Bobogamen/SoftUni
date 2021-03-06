package shoppinglist.view;

import shoppinglist.entity.enums.CategoryType;

public class ShoppingItem {

    private long id;
    private String name;
    private float price;
    private CategoryType category;

    private long ownerId;

    public ShoppingItem(long id, String name, float price, CategoryType category, long ownerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ownerId = ownerId;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
