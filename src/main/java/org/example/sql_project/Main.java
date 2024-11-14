package org.example.sql_project;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Database directory and name
        String dbDirectory = "database_files/";
        String dbName = "books.db"; // Student database name
        String full = dbDirectory + dbName;
        String fulldbPath = "jdbc:sqlite:" + full;

        // Connecting to the database
        try (Connection connection = DriverManager.getConnection(fulldbPath)) {
            if (connection != null) {
                System.out.println("Connected to database");

                // SQL to create students table with AUTO-INCREMENT ID and surname
                String createSQLTable = "CREATE TABLE IF NOT EXISTS book_details (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // id as auto-increment integer
                        "name VARCHAR(100) NOT NULL, " +  // name as VARCHAR
                        "authors VARCHAR(100) NOT NULL, " +  // name as VARCHAR
                        "publication_year VARCHAR(100) NOT NULL, " +  // name as VARCHAR
                        "copies_sold INTEGER NOT NULL, " +  // name as VARCHAR
                        "book_type VARCHAR(100) NOT NULL)";

                try (Statement statement = connection.createStatement()) {
                    statement.execute(createSQLTable);  // Execute the table creation
                } catch (SQLException e) {
                    System.out.println("Error creating table: " + e.getMessage());
                }

            } else {
                System.out.println("Had problems with connection");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        // Collecting student information
        System.out.print("Enter student name: ");
        String name = sc.nextLine();  // The name entered by the user
        System.out.print("Enter student surname: ");
        String authors = sc.nextLine();  // The surname entered by the user
        System.out.print("Enter student date of birth (YYYY-MM-DD): ");
        String publication_year = sc.nextLine();  // The date of birth entered by the user
        System.out.print("Enter student email: ");
        String copies_sold = sc.nextLine();  // The email entered by the user
        System.out.print("Enter student registration date: ");
        String book_type = sc.nextLine();

        // SQL statement to insert student data, ID is auto-incremented
        String insertSQLStatement = "INSERT INTO book_details (name, authors, publication_year, copies_sold, book_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(fulldbPath)) {
            try (PreparedStatement statement = connection.prepareStatement(insertSQLStatement)) {
                statement.setString(1, name);  // Set name as VARCHAR
                statement.setString(2, authors);  // Set surname as VARCHAR
                statement.setString(3, publication_year);  // Set date of birth as VARCHAR
                statement.setString(4, copies_sold);  // Set email as VARCHAR
                statement.setString(5, book_type);  // Set registrationDate as VARCHAR
                statement.executeUpdate();  // Execute the insertion
                System.out.println("Student data has been inserted successfully!");
            } catch (SQLException e) {
                System.out.println("Error inserting data: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}