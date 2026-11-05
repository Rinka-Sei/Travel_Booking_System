# Project details

## Rules: (any group violator will be set to 0%)
* No external libraries (SQL connector only)
* set Layout = Null
* Same code pattern on how i discuss during lesson
* No click and drag
* No existing data except for the admin user in the database
* All data that will be use in the project will be added via GUI as part of the project presentation

---

### Project Name: Online Movie Rental System

#### Project Description:
The Online Movie Rental System is an application designed to manage a digital library of movies, customers, and rentals. The system allows administrators to manage movie listings, customer information, and rental transactions. Additionally, customers can browse available movies, rent them, and return them when finished. The system will utilize various data structures and algorithms to enhance performance, such as sorting algorithms for movie listings and search algorithms for quick data retrieval.

#### Requirements:
The system should have a graphical user interface (GUI) to facilitate user interaction.

#### Functionalities:
* **Movie Management:**
  * Add, update, delete, and search for movies.
  * Categorize movies by genre, release year, or rating.
* **Customer Management:**
  * Add, update, delete, and search for customers.
  * Maintain customer rental history.
* **Rental Management:**
  * Rent and return movies.
  * Check availability of movies.
  * Generate reports on rentals, overdue movies, and customer activity.
* **Data Structure & Algorithm Utilization:**
  * Implement a binary search tree for efficient searching and sorting of movies by title or genre.
  * Use a hash table for quick access to customer records.
  * Apply a priority queue to manage rental transactions based on return dates.
* **GUI:**
  * Use Java Swing to create an interactive interface for users.

#### Classes:
* **Movie:** Represents a movie in the system with properties such as title, genre, release year, and availability status.
* **Customer:** Represents a customer with properties like name, contact information, and rental history (list of rented movies).
* **Rental:** Represents a rental transaction with properties such as customer, movie, rental date, and due date.
* **MovieCatalog:** Manages the collection of movies using a binary search tree or array list to store and manage movie data.
* **CustomerRegistry:** Manages customer records using a hash table for fast access and manipulation.
* **RentalService:** Handles the rental and return process, using a priority queue to track due dates and manage overdue rentals.
* **System:** Ties all components together and provides methods for interaction (adding, updating, searching, generating reports).

#### Data Structure and Algorithm Integration:
* **Sorting and Searching:** Implement sorting algorithms (e.g., quicksort or mergesort) to organize the movie listings, and use binary search for finding movies.
* **Hashing:** Use hash functions for quick customer lookups, ensuring efficient retrieval of customer data.
* **Priority Queue:** To manage overdue movies and prioritize notifications based on due dates.

#### Database Connection:
* Use Java JDBC API to connect to a MySQL database for persistent data storage and retrieval.

#### Exception Handling:
* Ensure that the system gracefully handles errors such as duplicate entries, invalid inputs, and database connection issues.

*This project not only resembles the Library Management System but also incorporates essential data structures and algorithms, enhancing its complexity and efficiency.*

---

## Group Projects

### G#1
#### 1. Inventory Management System
* **Description:** This system will manage products in a warehouse, allowing for efficient tracking of stock levels, orders, and suppliers.
* **Key Features:**
  * Add, update, delete, and search for inventory items.
  * Track supplier information and stock levels.
  * Generate low-stock and restock reports.
* **Class Structure:**
  * **Product:** Properties include id, name, quantity, supplier, and price.
  * **Supplier:** Properties include id, name, and contactInfo.
  * **InventoryManager:** Manages products and suppliers, with methods to add, update, and search for items.
* **Data Structures/Algorithms:**
  * Use a linked list for storing inventory items for dynamic memory allocation.
  * Implement a binary search tree for organizing products by category or price for efficient searching.
  * Use a hash map for quick access to supplier details.

---

### G#2
#### 2. Online Food Delivery System
* **Description:** A platform for managing restaurants, customers, and food orders, facilitating the entire delivery process.
* **Key Features:**
  * Browse menus, place orders, and track delivery status.
  * Manage restaurant profiles and customer accounts.
  * Generate reports on order history and delivery times.
* **Class Structure:**
  * **Restaurant:** Properties include id, name, menuItems, and location.
  * **Customer:** Properties include id, name, address, and orderHistory.
  * **Order:** Properties include orderId, customerId, restaurantId, orderItems, and status.
* **Data Structures/Algorithms:**
  * Use a hash table for quick restaurant lookups based on customer location.
  * Implement priority queues for managing orders based on delivery time and urgency.
  * Use a graph to optimize delivery routes.

---

### G#3
#### 3. School Management System
* **Description:** This system will manage student information, courses, and grades for educational institutions.
* **Key Features:**
  * Add, update, delete, and search for students and courses.
  * Maintain student grades and generate transcripts.
  * Generate attendance and performance reports.
* **Class Structure:**
  * **Student:** Properties include id, name, courses, and grades.
  * **Course:** Properties include courseId, courseName, and enrolledStudents.
  * **Grade:** Properties include studentId, courseId, and score.
* **Data Structures/Algorithms:**
  * Use an array list to store students and courses for dynamic access.
  * Implement binary search for grade lookups.
  * Use sorting algorithms to organize student names or course lists.

---

### G#4
#### 4. Hospital Management System
* **Description:** A system for managing patient records, doctor appointments, and treatment history in hospitals.
* **Key Features:**
  * Add, update, delete, and search for patients and doctors.
  * Schedule and manage appointments.
  * Generate reports on patient visits and treatments.
* **Class Structure:**
  * **Patient:** Properties include id, name, age, medicalHistory, and appointments.
  * **Doctor:** Properties include id, name, specialty, and patients.
  * **Appointment:** Properties include appointmentId, patientId, doctorId, and date.
* **Data Structures/Algorithms:**
  * Use a binary search tree for organizing patients by ID.
  * Implement priority queues for managing appointment scheduling based on urgency.
  * Use hashing for quick patient verification.

---

### G#5
#### 5. E-commerce Platform
* **Description:** A platform for managing products, user accounts, and transactions for online shopping.
* **Key Features:**
  * Browse products, add to cart, and process payments.
  * Manage user accounts and order history.
  * Generate sales and inventory reports.
* **Class Structure:**
  * **Product:** Properties include id, name, price, and category.
  * **User:** Properties include id, name, email, and orderHistory.
  * **Order:** Properties include orderId, userId, productList, and totalPrice.
* **Data Structures/Algorithms:**
  * Use a hash map for managing user sessions for quick access.
  * Implement sorting algorithms for product listings based on price or ratings.
  * Use binary search for finding products quickly.

---

### G#6
#### 6. Event Management System
* **Description:** This system will manage events, attendees, and registrations for various occasions.
* **Key Features:**
  * Add, update, delete, and search for events.
  * Manage attendee registrations and check-in.
  * Generate reports on attendance and feedback.
* **Class Structure:**
  * **Event:** Properties include eventId, name, date, and attendees.
  * **Attendee:** Properties include id, name, email, and eventId.
* **Data Structures/Algorithms:**
  * Use a linked list to manage event attendees for dynamic allocation.
  * Implement searching algorithms to find events by date or category.
  * Use a priority queue to manage waiting lists for popular events.

---

### G#7
#### 7. Fitness Tracker Application
* **Description:** An application that allows users to track workouts, meals, and fitness progress.
* **Key Features:**
  * Log workouts, track meals, and monitor progress.
  * Set fitness goals and receive notifications.
  * Generate progress reports based on user input.
* **Class Structure:**
  * **Workout:** Properties include workoutId, name, duration, and caloriesBurned.
  * **Meal:** Properties include mealId, name, calories, and nutritionalInfo.
  * **User:** Properties include id, name, workouts, and meals.
* **Data Structures/Algorithms:**
  * Use an array to store workout data for easy access.
  * Implement binary search for finding specific workouts or meals.
  * Use sorting algorithms to display meals based on nutritional content.

---

### G#8
#### 8. Online Voting System
* **Description:** A system for managing elections, candidates, and voter registrations, ensuring a secure and efficient voting process.
* **Key Features:**
  * Register voters, manage candidates, and facilitate voting.
  * Provide results and analytics post-elections.
* **Class Structure:**
  * **Voter:** Properties include voterId, name, and registered.
  * **Candidate:** Properties include candidateId, name, and party.
  * **Vote:** Properties include voteId, voterId, candidateId, and timestamp.
* **Data Structures/Algorithms:**
  * Use a graph to represent connections between voters and candidates.
  * Implement a hash table for quick voter verification and registration.
  * Use sorting algorithms to display candidates based on votes received.

---

### G#9
#### 9. Travel Booking System
* **Description:** A system for managing travel bookings, including flights, hotels, and packages.
* **Key Features:**
  * Search for flights and hotels, book tickets, and manage itineraries.
  * Track booking history and generate invoices.
* **Class Structure:**
  * **Flight:** Properties include flightId, departure, arrival, and price.
  * **Hotel:** Properties include hotelId, name, location, and rating.
  * **Booking:** Properties include bookingId, userId, flightId, and hotelId.
* **Data Structures/Algorithms:**
  * Use a binary search tree for managing flights by departure time.
  * Implement sorting algorithms for package deals based on price and features.
  * Use a hash table to manage customer records for quick access.

---

### G#10
#### 10. Real Estate Management System
* **Description:** A platform for managing real estate properties, clients, and transactions.
* **Key Features:**
  * Add, update, delete, and search for properties and clients.
  * Manage transactions and track property status.
  * Generate market analysis and sales reports.
* **Class Structure:**
  * **Property:** Properties include propertyId, location, price, and status.
  * **Client:** Properties include clientId, name, contactInfo, and propertiesOwned.
  * **Transaction:** Properties include transactionId, propertyId, clientId, and date.
* **Data Structures/Algorithms:**
  * Use a hash map for managing client records for fast access.
  * Implement binary search for finding properties by price or location.
  * Use sorting algorithms to generate reports on properties based on various metrics.

---

## Considerations for Each Project:
* Ensure you utilize object-oriented programming principles.
* Incorporate exception handling to manage errors gracefully.
* Connect to a database (e.g., MySQL) using JDBC for persistent data storage.
* Design a user-friendly GUI using Java Swing or a similar framework.

*These projects offer a wide range of functionalities and incorporate various data structures and algorithms, providing opportunities for learning and application of software development skills.*