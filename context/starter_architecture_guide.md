# Online Movie Rental System

## Starter / Prototype Project Structure

**Purpose:** This document provides a basic starter structure only. It demonstrates how students may organize the Java project, GUI, JDBC connection, classes, services, and Data Structures and Algorithms (DSA). It is intentionally incomplete so students still need to implement the required functionalities.

---

## Project Rules

- Any group violating the project rules will be set to 0%.
- No external libraries are allowed, except the required SQL/JDBC connector.
- Use `setLayout(null)` for the GUI.
- Follow the same basic code pattern demonstrated during lessons.
- No click-and-drag GUI builder/design approach.
- The database must contain no existing project data except the admin user.
- All project data must be added, updated, and deleted through the GUI.

---

## Project Description

The Online Movie Rental System is an application designed to manage a digital library of movies, customers, and rentals. Administrators can manage movie listings, customer information, and rental transactions. Customers can browse available movies, rent them, and return them when finished. The system uses data structures and algorithms such as sorting, searching, a binary search tree, hash table, and priority queue.

---

## Functionalities

### Movie Management

- Add, update, delete, and search for movies.
- Categorize movies by genre, release year, or rating.

### Customer Management

- Add, update, delete, and search for customers.
- Maintain customer rental history.

### Rental Management

- Rent and return movies.
- Check movie availability.
- Generate reports for rentals, overdue movies, and customer activity.

### DSA Utilization

- Binary search tree for movie searching/sorting.
- Hash table for quick customer access.
- Priority queue for rental transactions based on due dates.

---

## Recommended Project Structure

    OnlineMovieRentalSystem/
    ├── src/
    │   ├── model/
    │   │   ├── Movie.java
    │   │   ├── Customer.java
    │   │   └── Rental.java
    │   ├── ds/
    │   │   ├── MovieBST.java
    │   │   ├── CustomerHashTable.java
    │   │   └── RentalPriorityQueue.java
    │   ├── service/
    │   │   ├── MovieCatalog.java
    │   │   ├── CustomerRegistry.java
    │   │   └── RentalService.java
    │   ├── database/
    │   │   └── DatabaseConnection.java
    │   ├── gui/
    │   │   ├── LoginFrame.java
    │   │   ├── MainFrame.java
    │   │   ├── MoviePanel.java
    │   │   ├── CustomerPanel.java
    │   │   └── RentalPanel.java
    │   └── Main.java
    └── database/
        └── database.sql

---

## Starter Class Examples

### Movie.java

    public class Movie {
        private int movieId;
        private String title;
        private String genre;
        private int releaseYear;
        private double rating;
        private boolean available;

        public Movie(int movieId, String title, String genre,
                     int releaseYear, double rating) {
            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.releaseYear = releaseYear;
            this.rating = rating;
            this.available = true;
        }

        // Getters and setters
    }

### MovieBST.java

    public class MovieBST {
        private class Node {
            Movie movie;
            Node left;
            Node right;

            Node(Movie movie) {
                this.movie = movie;
            }
        }

        private Node root;

        public void insert(Movie movie) {
            // STUDENT IMPLEMENTATION HERE
        }

        public Movie search(String title) {
            // STUDENT IMPLEMENTATION HERE
            return null;
        }

        public void displayInOrder() {
            // STUDENT IMPLEMENTATION HERE
        }
    }

### CustomerHashTable.java

    public class CustomerHashTable {
        private Customer[] table;

        public CustomerHashTable(int size) {
            table = new Customer[size];
        }

        private int hashFunction(int customerId) {
            return customerId % table.length;
        }

        public void insert(Customer customer) {
            // STUDENT IMPLEMENTATION HERE
        }

        public Customer search(int customerId) {
            // STUDENT IMPLEMENTATION HERE
            return null;
        }

        public void delete(int customerId) {
            // STUDENT IMPLEMENTATION HERE
        }
    }

### RentalPriorityQueue.java

    import java.util.PriorityQueue;

    public class RentalPriorityQueue {
        private PriorityQueue<Rental> rentalQueue;

        public RentalPriorityQueue() {
            rentalQueue = new PriorityQueue<>();
        }

        public void addRental(Rental rental) {
            // STUDENT IMPLEMENTATION HERE
        }

        public Rental getNextDueRental() {
            // STUDENT IMPLEMENTATION HERE
            return null;
        }
    }

---

## Service Layer Starter

    public class MovieCatalog {
        private MovieBST movieBST;

        public MovieCatalog() {
            movieBST = new MovieBST();
        }

        public void addMovie(Movie movie) {
            // Save to database
            // Add to BST
            // STUDENT IMPLEMENTATION HERE
        }

        public Movie searchMovie(String title) {
            return movieBST.search(title);
        }

        public void deleteMovie(int movieId) {
            // STUDENT IMPLEMENTATION HERE
        }
    }

---

## JDBC Connection Starter

    import java.sql.Connection;
    import java.sql.DriverManager;

    public class DatabaseConnection {
        private static final String URL =
            "jdbc:mysql://localhost:3306/movie_rental";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
                );
            } catch (Exception e) {
                System.out.println("Database connection failed.");
                return null;
            }
        }
    }

---

## Java Swing GUI Starter

    import javax.swing.*;

    public class MainFrame extends JFrame {
        public MainFrame() {
            setTitle("Online Movie Rental System");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            // STUDENT IMPLEMENTATION HERE
            setVisible(true);
        }
    }

---

## Example GUI Component

    JButton btnMovies = new JButton("Movies");
    btnMovies.setBounds(50, 50, 150, 40);
    add(btnMovies);

---

## Database Starter

    CREATE DATABASE movie_rental;

    USE movie_rental;

    CREATE TABLE admin (
        admin_id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(50) UNIQUE NOT NULL,
        password VARCHAR(100) NOT NULL
    );

    CREATE TABLE movies (
        movie_id INT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(100) NOT NULL,
        genre VARCHAR(50),
        release_year INT,
        rating DOUBLE,
        available BOOLEAN DEFAULT TRUE
    );

    CREATE TABLE customers (
        customer_id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        contact VARCHAR(50)
    );

    CREATE TABLE rentals (
        rental_id INT PRIMARY KEY AUTO_INCREMENT,
        customer_id INT,
        movie_id INT,
        rental_date DATE,
        due_date DATE,
        return_date DATE,
        status VARCHAR(20)
    );

    INSERT INTO admin (username, password)
    VALUES ('admin', 'admin123');

---

## Important Database Rule

Only the admin account should be pre-existing. Do not insert sample movies, customers, or rentals into the database. Students must demonstrate adding the required project data through the GUI.

---

## What the Starter Provides vs. What Students Must Implement

| Component | Starter Provides | Students Implement |
| --- | --- | --- |
| Movie | Class structure | Complete properties/methods |
| Customer | Class structure | Complete properties/methods |
| Rental | Class structure | Complete properties/methods |
| BST | Node + method skeleton | BST operations |
| Hash Table | Array + hash function | Collision handling, CRUD/search |
| Priority Queue | Queue declaration | Rental prioritization |
| JDBC | Connection pattern | CRUD SQL queries |
| GUI | Basic frame/panel pattern | Complete GUI |
| Movie Management | Method skeletons | Add/update/delete/search |
| Customer Management | Method skeletons | Add/update/delete/search |
| Rental | Method skeletons | Rent/return/availability |
| Reports | Placeholder | Implement reports |
| Exception Handling | Basic pattern | Validation and error handling |

---

**Reminder:** This is a starter/reference structure, not a complete project solution. Groups are expected to understand, modify, and implement the required functionalities and DSA integration themselves.