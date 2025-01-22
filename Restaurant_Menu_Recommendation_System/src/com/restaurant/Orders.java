package com.restaurant;

import java.util.HashMap;

public class Orders {
    private static int orderCounter = 0;
    private int orderId;
    private Users user;
    private HashMap<MenuItems, Integer> orderItems; 
    private double totalCost;
    private String status; 
    public Orders(Users user) {
        this.orderId = ++orderCounter;
        this.user = user;
        this.orderItems = new HashMap<>();
        this.totalCost = 0.0;
        this.status = "Pending";
    }
    public void addItem(MenuItems item, int quantity) {
        if (quantity > 0) {
            orderItems.put(item, orderItems.getOrDefault(item, 0) + quantity);
            totalCost += item.getPrice() * quantity;
        }
    }

    // Remove an item from the order
    public void removeItem(MenuItems item, int quantity) {
        if (orderItems.containsKey(item)) {
            int currentQuantity = orderItems.get(item);
            if (quantity >= currentQuantity) {
                totalCost -= item.getPrice() * currentQuantity;
                orderItems.remove(item);
            } else {
                orderItems.put(item, currentQuantity - quantity);
                totalCost -= item.getPrice() * quantity;
            }
        }
    }
    public void completeOrder() {
        this.status = "Completed";
        System.out.println("Order ID " + orderId + " has been completed.");
    }
    public void cancelOrder() {
        this.status = "Cancelled";
        System.out.println("Order ID " + orderId + " has been cancelled.");
    }
    public void displayOrderSummary() {
        System.out.println("Order ID: " + orderId);
        System.out.println("User: " + user.getName());
        System.out.println("Status: " + status);
        System.out.println("Ordered Items:");
        for (MenuItems item : orderItems.keySet()) {
            System.out.println("- " + item.getDishName() + " (Quantity: " + orderItems.get(item) + ")");
        }
        System.out.println("Total Cost: $" + totalCost);
    }
}