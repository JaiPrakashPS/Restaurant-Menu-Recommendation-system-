package com.restaurant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private Connection connection;

    // Private constructor to prevent direct instantiation
    private Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_menu_recommendation", "root", "jai1466@1");
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Singleton instance retrieval
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Method to load initial data, for example, load users
    public void loadInitialData() {
        if (connection != null) {
            loadUsers();
   } else {
            System.out.println("Cannot load initial data; connection is not established.");
        }
    }

    // Method to load users from the database
    public void loadUsers() {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";  // Modify the query based on your users table structure
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Users loaded successfully.");
            while (resultSet.next()) {
                // Process user data here if needed
            }
        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to add a user to the database
    public void addUser(Users user) {
        try {
            String query = "INSERT INTO users (name, dietaryPreference) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getDietaryPreference());
                preparedStatement.executeUpdate();

                // Retrieve the generated user ID and assign it to the user object
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
 user.setUserId(generatedKeys.getInt(1));
                }
                System.out.println("User added to database with ID: " + user.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to update the user's dietary preferences in the database
    public void updateUserPreferences(Users user) {
        try {
            // Ensure the users table has a column userId as the primary key
            String query = "UPDATE users SET dietaryPreference = ? WHERE userId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getDietaryPreference());
                preparedStatement.setInt(2, user.getUserId());  // Ensure this matches your user object
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Updated preferences for user: " + user.getUserId());
                } else {
                    System.out.println("No user found with the given userId.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating user preferences: " + e.getMessage());
            e.printStackTrace();
        }
    }
public List<MenuItems> getRecommendedDishes(Users user) {
        List<MenuItems> recommendedDishes = new ArrayList<>();
        try {
            String query = "SELECT id, dishName, type, cuisine, dietaryPreference, price FROM menu_items WHERE dietaryPreference = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getDietaryPreference());

                System.out.println("Executing query: " + preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    // Instantiate MenuItems with all necessary columns
                    MenuItems item = new MenuItems(
                            resultSet.getInt("id"),
                            resultSet.getString("dishName"),
                            resultSet.getString("type"),
                            resultSet.getString("cuisine"),
                            resultSet.getString("dietaryPreference"),
                            resultSet.getDouble("price")
                    );
                    recommendedDishes.add(item);
                }
                System.out.println("Recommended dishes retrieved for user ID: " + user.getUserId());
 }
        } catch (SQLException e) {
            System.out.println("Error retrieving recommended dishes: " + e.getMessage());
            e.printStackTrace();
        }
        return recommendedDishes;
    }

    // Method to submit feedback for a menu item by a user
    public void submitFeedback(Users user, int menuItemId, int rating, String feedback) {
        try {
            String query = "INSERT INTO userfeedback (user_id, menu_item_id, rating, feedback) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, menuItemId);
                preparedStatement.setInt(3, rating);
                preparedStatement.setString(4, feedback);
                preparedStatement.executeUpdate();
                System.out.println("Feedback submitted for menu item ID: " + menuItemId + " by user ID: " + user.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Error submitting feedback: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<MenuItems> getRecommendedDishes1(Users user) {
        List<MenuItems> recommendedDishes = new ArrayList<>();
        
        
        try {
            String query = "SELECT id, dishName, type, cuisine, dietaryPreference, price FROM menu_items WHERE dietaryPreference = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (user.getDietaryPreference() != null) {
                    preparedStatement.setString(1, user.getDietaryPreference());
                } else {
                    preparedStatement.setNull(1, Types.VARCHAR);  // Optionally, use a default if dietaryPreference is null
                }

                System.out.println("Executing query: " + preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    MenuItems item = new MenuItems(
                        resultSet.getInt("id"),
                        resultSet.getString("dishName"),
                        resultSet.getString("type"),
                        resultSet.getString("cuisine"),
                        resultSet.getString("dietaryPreference"),
                        resultSet.getDouble("price")
                    );
                    recommendedDishes.add(item);
                }
                System.out.println("Recommended dishes retrieved for user ID: " + user.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving recommended dishes: " + e.getMessage());
            e.printStackTrace();
        }
        return recommendedDishes;
    }


}