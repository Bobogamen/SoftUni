package bg.softuni.com.shoppinglist.view;

import bg.softuni.com.shoppinglist.entity.enums.CategoryType;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingList {

    private List<ShoppingItem> food;
    private List<ShoppingItem> drink;
    private List<ShoppingItem> household;
    private List<ShoppingItem> other;
    private float totalPrice;

    public ShoppingList(List<ShoppingItem> shoppingItems) {
        this.food = getList(shoppingItems, CategoryType.FOOD);
        this.drink = getList(shoppingItems, CategoryType.DRINK);;
        this.household = getList(shoppingItems, CategoryType.HOUSEHOLD);;
        this.other = getList(shoppingItems, CategoryType.OTHER);;
        this.totalPrice = shoppingItems.stream()
                .map(ShoppingItem::getPrice).reduce((sum, price) -> sum += price).orElse(null);
    }

    public List<ShoppingItem> getFood() {
        return food;
    }

    public void setFood(List<ShoppingItem> food) {
        this.food = food;
    }

    public List<ShoppingItem> getDrink() {
        return drink;
    }

    public void setDrink(List<ShoppingItem> drink) {
        this.drink = drink;
    }

    public List<ShoppingItem> getHousehold() {
        return household;
    }

    public void setHousehold(List<ShoppingItem> household) {
        this.household = household;
    }

    public List<ShoppingItem> getOther() {
        return other;
    }

    public void setOther(List<ShoppingItem> other) {
        this.other = other;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private List<ShoppingItem> getList(List<ShoppingItem> shoppingItems, CategoryType category) {
        return shoppingItems.stream().filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
    }



}
