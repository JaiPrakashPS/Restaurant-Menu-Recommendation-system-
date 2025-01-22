package com.restaurant;

import java.util.ArrayList;

public class MenuItems {
    private static int dishCounter = 0;
    private int dishId;
    private String dishName;
    private String type; 
    private String cuisine;
    private String dietaryInfo; 
    private double price;
    private int popularityScore;
    private ArrayList<Feedback> feedbackList; 
    public MenuItems(int dishId, String dishName, String type, String cuisine, String dietaryInfo, double price) {
        this.dishId = dishId;  
        this.dishName = dishName;
        this.type = type;
        this.cuisine = cuisine;
        this.dietaryInfo = dietaryInfo;
        this.price = price;
        this.popularityScore = 0;
        this.feedbackList = new ArrayList<>();
    }
    public MenuItems(String dishName, String type, String cuisine, String dietaryInfo, double price) {
        this.dishId = ++dishCounter;
        this.dishName = dishName;
        this.type = type;
        this.cuisine = cuisine;
        this.dietaryInfo = dietaryInfo;
        this.price = price;
        this.popularityScore = 0;
        this.feedbackList = new ArrayList<>();
    }
    public int getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public String getType() { return type; }
    public String getCuisine() { return cuisine; }
    public String getDietaryInfo() { return dietaryInfo; }
    public double getPrice() { return price; }
    public int getPopularityScore() { return popularityScore; }

    public void setDishName(String dishName) { this.dishName = dishName; }
    public void setType(String type) { this.type = type; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }
    public void setDietaryInfo(String dietaryInfo) { this.dietaryInfo = dietaryInfo; }
    public void setPrice(double price) { this.price = price; }
    public void setPopularityScore(int popularityScore) { this.popularityScore = popularityScore; }
    public void addFeedback(Users user, int rating, String comments) {
        Feedback feedback = new Feedback(user, rating, comments);
        feedbackList.add(feedback);
        updatePopularityScore();
    }
    private void updatePopularityScore() {
        int totalRating = 0;
        for (Feedback feedback : feedbackList) {
            totalRating += feedback.getRating();
        }
        this.popularityScore = feedbackList.size() > 0 ? totalRating / feedbackList.size() : 0;
    }
    public void displayFeedback() {
        System.out.println("Feedback for Dish: " + dishName);
        for (Feedback feedback : feedbackList) {
            System.out.println("- User ID: " + feedback.getUser().getUserId());
            System.out.println("  Rating: " + feedback.getRating());
            System.out.println("  Comments: " + feedback.getComments());
        }
    }
    private class Feedback {
        private Users user;
        private int rating;
        private String comments;

        public Feedback(Users user, int rating, String comments) {
            this.user = user;
            this.rating = rating;
            this.comments = comments;
        }

        public Users getUser() { return user; }
        public int getRating() { return rating; }
        public String getComments() { return comments; }
    }

    public int getDishId1() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
