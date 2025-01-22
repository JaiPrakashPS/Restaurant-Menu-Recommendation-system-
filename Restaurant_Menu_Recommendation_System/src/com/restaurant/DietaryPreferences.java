package com.restaurant;

import java.util.ArrayList;
import java.util.List;

public class DietaryPreferences {
    private Users user;
    private String dietaryType;
    private List<String> cuisinePreferences;
    private List<String> foodAllergies; 

    // Constructor
    public DietaryPreferences(Users user, String dietaryType) {
        this.user = user;
        this.dietaryType = dietaryType;
        this.cuisinePreferences = new ArrayList<>();
        this.foodAllergies = new ArrayList<>();
    }
    public void addCuisinePreference(String cuisine) {
        if (!cuisinePreferences.contains(cuisine)) {
            cuisinePreferences.add(cuisine);
        }
    }
    public void removeCuisinePreference(String cuisine) {
        cuisinePreferences.remove(cuisine);
    }

    public void addFoodAllergy(String allergy) {
        if (!foodAllergies.contains(allergy)) {
            foodAllergies.add(allergy);
        }
    }
    public void removeFoodAllergy(String allergy) {
        foodAllergies.remove(allergy);
    }
    public Users getUser() {
        return user;
    }

    public String getDietaryType() {
        return dietaryType;
    }

    public List<String> getCuisinePreferences() {
        return cuisinePreferences;
    }

    public List<String> getFoodAllergies() {
        return foodAllergies;
    }
    public void setDietaryType(String dietaryType) {
        this.dietaryType = dietaryType;
    }
    public String getDietaryPreference() {
        StringBuilder preferenceSummary = new StringBuilder();
        preferenceSummary.append("Dietary Type: ").append(dietaryType).append("\n")
                         .append("Cuisine Preferences: ").append(String.join(", ", cuisinePreferences)).append("\n")
                         .append("Food Allergies: ").append(String.join(", ", foodAllergies));
        return preferenceSummary.toString();
    }
    public void displayPreferences() {
        System.out.println("Dietary Preferences for User ID: " + user.getUserId());
        System.out.println(getDietaryPreference());
    }
}
