package com.restaurant;

import java.util.Scanner;

public class Restaurant {
    public static void main(String[] args) {
        Database db = Database.getInstance();
        db.loadInitialData();
        
        Users user = new Users("Guest"); // Placeholder for starting without a user
        user.start();
    }
}

