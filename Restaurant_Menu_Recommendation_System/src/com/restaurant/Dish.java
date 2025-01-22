package com.restaurant;

public class Dish {
    private static int dishCounter = 0;
    private int dishId;
    private String dishName;
    private String item; 
    public Dish(String dishName, String item) {
        this.dishId = ++dishCounter;
        this.dishName = dishName;
        this.item = item;
    }
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Dish ID: " + dishId + ", Dish Name: " + dishName + ", Item: " + item;
    }
}
