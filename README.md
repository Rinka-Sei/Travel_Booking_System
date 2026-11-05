# Travel Booking System (Java) — Group 9 Framework

An educational Java Swing travel-booking system for managing flights, hotels,
package deals, and bookings. It demonstrates JDBC persistence, a flight BST,
a customer hash table, and hand-written package QuickSort.

---

[![VIEW API DOCS](https://img.shields.io/badge/View-API%20Docs-blue?style=for-the-badge)](https://rinka-sei.github.io/dsa_travel_booking_system-beta/)

Hosted interactive API documentation and architectural guide: [https://rinka-sei.github.io/dsa_travel_booking_system-beta/](https://rinka-sei.github.io/dsa_travel_booking_system-beta/)

## 🚀 Key Functional Features

- **Flight and Hotel CRUD:** Admin users can add, update, delete, search, and
  sort travel listings.
- **Package Deal Sorting:** Destination-matched flight and hotel packages can
  be sorted by price or rating with toggleable QuickSort direction.
- **Booking Management:** Customers can create, update, cancel, inspect
  itineraries, and generate invoices for bookings.
- **DSA and Reflection Integration:** `FlightBST`, `CustomerHashTable`, and
  `PackageQuickSort` are wired through the service/GUI layers with JDBC
  fallback behavior.
- **Enterprise Documentation and Tests:** Source classes include JavaDoc and
  custom DSA behavior is covered by JUnit 5 tests.

---

## 📐 Modular Package Structure

```text
dsa_travel_booking_system/
├── src/
│   ├── main/java/
│   │   ├── database/DBConnection.java
│   │   ├── ds/
│   │   │   ├── CustomerHashTable.java
│   │   │   ├── FlightBST.java (Work-In-Progress)
│   │   │   └── PackageQuickSort.java (Work-In-Progress)
│   │   ├── gui/
│   │   │   ├── LoginFrame.java
│   │   │   ├── MainFrame.java
│   │   │   ├── FlightPanel.java
│   │   │   ├── HotelPanel.java
│   │   │   ├── PackagePanel.java
│   │   │   └── BookingPanel.java
│   │   ├── model/
│   │   ├── service/
│   │   └── Main.java
│   └── test/java/ds/
├── database/
│   └── 01_admin.sql
├── pom.xml
├── README.md
└── CHANGELOG.md
```

---

## 📜 Strict Professor Rule Compliance

1. **Absolute Layout Positioning (`setLayout(null)`):** Every frame and panel
   uses explicit coordinate layout and `setBounds(...)`; no visual GUI builder
   is used.
2. **Zero External Libraries:** Runtime code uses standard JDK APIs plus
   `mysql-connector-j`; JUnit 5 is the only test dependency.
3. **GUI Live-Seeding Rule:** `database/01_admin.sql` creates the schema and
   pre-seeds only `admin` / `admin123`. No operational flights, hotels,
   customers, or bookings are pre-seeded, and no `02_*.sql` sample-data script
   is included.
4. **CRUD and DSA:** Flight, hotel, and booking workflows include update
   support with table-selection auto-fill; the required Item 9 DSA classes are
   hand-written and service reflection has JDBC fallback behavior.

---

## 🛠️ Database Setup & Execution

### Database Setup Scripts

```bash
mysql -u root < database/01_admin.sql
```

### Build & Run Commands

```bash
# Compile project
mvn compile

# Launch application
mvn exec:java

# Run JUnit 5 unit test suite
mvn test
```

---

## 📄 License

This repository is licensed under the open-source [MIT License](LICENSE).
