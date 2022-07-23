package com.onlineshop.model.dto;

public class UsersInfoDTO {
    private final long  id;
    private String username;
    private String name;
    private int addressCount;
    private int orderCount;
    private double totalSumOrders;

    public UsersInfoDTO(long id, String username, String name, int addressCount, int orderCount, double totalSumOrders) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.addressCount = addressCount;
        this.orderCount = orderCount;
        this.totalSumOrders = totalSumOrders;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddressCount() {
        return addressCount;
    }

    public void setAddressCount(int addressCount) {
        this.addressCount = addressCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getTotalSumOrders() {
        return totalSumOrders;
    }

    public void setTotalSumOrders(double totalSumOrders) {
        this.totalSumOrders = totalSumOrders;
    }

    public long getId() {
        return id;
    }

}
