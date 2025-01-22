package com.restaurant;

import java.util.Scanner;

public class Users {
    private int userId;
    private String name;
    private String dietaryPreference;

    public Users(String name) {
        this.name = name;
    }

    public void addToDatabase() {
        Database db = Database.getInstance();
        db.addUser(this);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public void updatePreferences(String newPreference) {
        this.dietaryPreference = newPreference;
        Database db = Database.getInstance();
        db.updateUserPreferences(this);
        System.out.println("Updated preferences for user: " + this.userId);
    }

    public void submitFeedback(int menuItemId, int rating, String feedback) {
        Database db = Database.getInstance();
        db.submitFeedback(this, menuItemId, rating, feedback);
        System.out.println("Feedback submitted for item ID " + menuItemId + " by user " + this.userId);
    }

    public void viewRecommendations() {
        Database db = Database.getInstance();
        System.out.println("Recommendations for user " + this.userId + ":");
        for (MenuItems item : db.getRecommendedDishes(this)) {
            System.out.println("- " + item.getDishName() + " (" + item.getCuisine() + ")");
        }
    }

    public static Users getUserById(int userId) {
        Database db = Database.getInstance();
        return db.getUserById(userId);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. View Recommendations");
            System.out.println("3. Update Dietary Preferences");
            System.out.println("4. Submit Feedback");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addNewUser();
                    break;
                case 2:
                    System.out.print("Enter User ID: ");
                    int userIdForRecommendations = scanner.nextInt();
                    Users userForRecommendations = Users.getUserById(userIdForRecommendations);
                    if (userForRecommendations != null) {
                        userForRecommendations.viewRecommendations();
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    int userIdForUpdate = scanner.nextInt();
                    Users userForUpdate = Users.getUserById(userIdForUpdate);
                    if (userForUpdate != null) {
                        System.out.print("Enter new dietary preference: ");
                        String newPreference = scanner.nextLine();
                        userForUpdate.updatePreferences(newPreference);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int userIdForFeedback = scanner.nextInt();
                    Users userForFeedback = Users.getUserById(userIdForFeedback);
                    if (userForFeedback != null) {
                        System.out.print("Enter Menu Item ID: ");
                        int menuItemId = scanner.nextInt();
                        System.out.print("Enter Rating (1-5): ");
                        int rating = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Feedback: ");
                        String feedback = scanner.nextLine();
                        userForFeedback.submitFeedback(menuItemId, rating, feedback);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void addNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your dietary preference: ");
        String dietaryPreference = scanner.nextLine();

        Users newUser = new Users(name);
        newUser.setDietaryPreference(dietaryPreference);
        newUser.addToDatabase();
    }
}
