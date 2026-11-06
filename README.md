# Travel Booking System (Java) — Group 9 Framework

An educational Java Swing travel-booking system for managing flights, hotels, package deals, and bookings. It demonstrates JDBC persistence, a flight BST, a customer hash table, and a hand-written package QuickSort.

---

[![VIEW API DOCS](https://img.shields.io/badge/View-API%20Docs-blue?style=for-the-badge)](https://rinka-sei.github.io/dsa_travel_booking_system-beta/)

Hosted interactive API documentation and architectural guide: [https://rinka-sei.github.io/dsa_travel_booking_system-beta/](https://rinka-sei.github.io/dsa_travel_booking_system-beta/)

## 🚀 Key Functional Features

- **Flight and Hotel CRUD:** Admin users can add, update, delete, search, and sort travel listings with table-selection auto-fill.
- **Package Deal Sorting:** Destination-matched flight and hotel packages can be sorted by price or rating with toggleable QuickSort direction.
- **Booking Management:** Customers can create, update, cancel, inspect itineraries, and generate invoices for bookings.
- **Admin-Only Reporting:** The executive report on the `Bookings & Itinerary` tab is restricted to accounts with the `ADMIN` role.
- **DSA and Reflection Integration:** `FlightBST`, `CustomerHashTable`, and `PackageQuickSort` are wired through the service and GUI layers with JDBC fallback behavior.
- **Enterprise Documentation:** Source classes include JavaDoc; custom DSA behavior is covered by JUnit 5 tests in the upstream nightly repository.

---

## 📐 Modular Package Structure

    dsa_travel_booking_system-beta/
    ├── database/
    │   └── 01_admin.sql
    ├── src/
    │   └── main/
    │       └── java/
    │           ├── database/
    │           │   └── DBConnection.java
    │           ├── ds/
    │           │   ├── CustomerHashTable.java
    │           │   ├── FlightBST.java
    │           │   └── PackageQuickSort.java
    │           ├── gui/
    │           │   ├── BookingPanel.java
    │           │   ├── FlightPanel.java
    │           │   ├── HotelPanel.java
    │           │   ├── LoginFrame.java
    │           │   ├── MainFrame.java
    │           │   └── PackagePanel.java
    │           ├── model/
    │           │   ├── Booking.java
    │           │   ├── Flight.java
    │           │   ├── Hotel.java
    │           │   ├── PackageDeal.java
    │           │   └── User.java
    │           ├── service/
    │           │   ├── BookingManagementService.java
    │           │   ├── CustomerRegistryService.java
    │           │   └── FlightCatalogService.java
    │           └── Main.java
    ├── .gitignore
    ├── CHANGELOG.md
    ├── LICENSE
    ├── nbactions.xml
    ├── pom.xml
    └── README.md

---

## 📜 Strict Professor Rule Compliance

1. **Absolute Layout Positioning (`setLayout(null)`):** Every frame and panel uses explicit coordinate layout (`setLayout(null)`) and manual component placement via `setBounds(x, y, width, height)`. No visual GUI builders are used.
2. **Zero External Libraries (JDBC Connector Only):** Relies solely on standard JDK 21+ APIs and `mysql-connector-j` as the sole external dependency. JUnit 5 is the only test dependency.
3. **GUI Live-Seeding Rule:** Database setup scripts are located in the root `database/` directory (`database/01_admin.sql` pre-seeding default admin `admin` / `admin123`). No operational flights, hotels, customers, or bookings are pre-seeded, and no `02_*.sql` sample-data script is included.

---

## 🛠️ Database Setup & Execution

### Database Setup Scripts

Execute `database/01_admin.sql` to initialize the schema and seed the admin user:

    mysql -u root < database/01_admin.sql

### Build & Run Commands

    # Compile project
    mvn compile

    # Launch application
    mvn exec:java

    # Run JUnit 5 unit test suite (tests live in the nightly repository)
    mvn test

---

## 📄 License

This repository is licensed under the open-source [MIT License](LICENSE).