# Online Movie Rental System — Master Framework & Domain Adaptation Blueprint
# (`master_compliance_blueprint.md`)

  This document is the authoritative technical reference and architectural blueprint for the
Java Swing + Custom DSA + JDBC **Online Movie Rental System** described in `project_roster_and_topics.md`
and `starter_architecture_guide.md`, together with the **Project Adaptation Catalogs** for the 9 other
domain items assigned by the professor. Every rule in this document is traceable to
`project_roster_and_topics.md` or `starter_architecture_guide.md`; no rule is introduced from external sources.

---

## 1. Absolute Project Rules & Constraints (Non-Negotiable)

Any system built upon or adapted from this blueprint — **for any of the 9 project items
listed in §11** — must strictly follow these rules. Any violation results in an automatic
**0%** (as stated verbatim in `project_roster_and_topics.md` and `starter_architecture_guide.md`).

| Rule | Requirement | Enforcement |
| :--- | :--- | :--- |
| **Zero External Libraries** | Only standard JDK APIs plus the required MySQL JDBC connector (`mysql-connector-j`). | Importing third-party UI libraries (FlatLaf, MigLayout), collection utilities (Guava, Apache Commons), logging frameworks, or mocking frameworks is prohibited. `pom.xml` must declare `mysql-connector-j` as the sole runtime dependency. |
| **Absolute GUI Layout** | Every frame and panel must use `setLayout(null)` and explicit `setBounds(x, y, width, height)`. | No drag-and-drop form builders (NetBeans GUI Builder, IntelliJ GUI Designer, WindowBuilder). Windows must be generously sized to prevent squished controls. |
| **Same Code Pattern as Lessons** | Follow the same basic code pattern demonstrated during lessons: `implements ActionListener` + `actionPerformed(ActionEvent e)` on every interactive panel; explicit listener wiring; explicit `add(...)` of components. | Instructor-graded; deviating from the demonstrated pattern is a rule violation. |
| **5-Tier Folder Layout** | Strict separation into `model/`, `ds/`, `service/`, `database/`, `gui/`, plus root `database/*.sql` and `Main.java`. | Must match the recommended project structure in `starter_architecture_guide.md` (§4). |
| **Database Seeding Rule** | Setup scripts live in root `database/`. The only pre-existing record may be the admin user (`admin` / `admin123`). All domain entities (movies, customers, rentals — or the equivalent entity set for other items) must be created, updated, and deleted **through the GUI**. | `database/01_admin.sql` must contain only the schema and the single admin row. Any pre-inserted operational record is a rule violation. There is **no** `02_customers.sql` sample-data script — that would violate `starter_architecture_guide.md`. |
| **Full End-to-End CRUD** | Complete Create, Read, Update, Delete for every primary entity in the domain. | Each primary panel must include an **"Update Selected"** feature with table row selection auto-fill. |
| **Exception Handling** | Gracefully handle duplicate entries, invalid inputs, and database connection issues with descriptive `JOptionPane` dialogs or logged messages. | Required per `project_roster_and_topics.md` → "Exception Handling" section and `starter_architecture_guide.md` → "Exception Handling" table row. |
| **Scope Discipline** | Each project item (§11) uses **only** the entities, DSA structures, and services assigned to it by the professor's group-project sheet. Do not import entities from other items. | Cross-domain mixing (e.g., adding a `Flight` entity to the Movie Rental System) is a scope violation. |

---

## 2. Anchor Project Scope — Online Movie Rental System (`project_roster_and_topics.md`)

**Project Name:** Online Movie Rental System

**Description:** Manage a digital library of movies, customers, and rental
transactions. Administrators manage movie listings, customers, and rentals;
customers browse available movies, rent them, and return them.

### Required Functionalities (verbatim)

**Movie Management**
- Add, update, delete, and search for movies.
- Categorize movies by genre, release year, or rating.

**Customer Management**
- Add, update, delete, and search for customers.
- Maintain customer rental history.

**Rental Management**
- Rent and return movies.
- Check movie availability.
- Generate reports on rentals, overdue movies, and customer activity.

### Required DSA Utilization (verbatim)
- **Binary Search Tree** for efficient searching and sorting of movies by title or genre.
- **Hash table** for quick access to customer records.
- **Priority queue** to manage rental transactions based on return dates (overdue prioritization).

### Required Non-Functional Requirements
- Java Swing GUI, absolute layout, no drag-and-drop.
- MySQL persistence via JDBC.
- Exception handling for duplicates, invalid input, and connection failures.

---

## 3. Required Class Structure — Anchor Project (`project_roster_and_topics.md`)

### Model (`model/`)
- **`Movie`** — title, genre, release year, availability status, rating
  (mirrors `movies` table in `starter_architecture_guide.md`: `movie_id INT PK AUTO_INCREMENT`).
- **`Customer`** — name, contact information, rental history (list of rented movies).
  `customerId` mirrors `customer_id INT PK AUTO_INCREMENT`.
- **`Rental`** — customer, movie, rental date, due date, and return date/status
  (mirrors `rentals` table in `starter_architecture_guide.md`).

### Data Structures (`ds/`)
- **`MovieBST`** — binary search tree keyed on movie title (or genre; pick one and
  document it). Must implement `insert`, `search`, `delete`, and an in-order traversal
  that produces a sorted list for the GUI.
- **`CustomerHashTable`** — custom hash table with **separate-chaining collision
  handling** for O(1)-average customer lookup by ID. Must implement `put`, `get`,
  `remove`, and iteration for report generation. Do **not** substitute
  `java.util.HashMap` for this class.
- **`RentalPriorityQueue`** — priority queue (min-heap on `dueDate`) managing rentals
  by return date so overdue rentals surface first. Starter provides the
  `PriorityQueue<Rental>` declaration in `RentalPriorityQueue.java`; students must
  implement `addRental` and `getNextDueRental`. Using `java.util.PriorityQueue` as
  the backing store is permitted per the starter skeleton.

### Services (`service/`)
- **`MovieCatalog`** — manages the movie collection. Uses `MovieBST` for search and
  sorting. Exposes `addMovie`, `searchMovie(String title)`, `deleteMovie(int movieId)`,
  `updateMovie(...)`, `listSorted()`. Persists via JDBC and mirrors inserts/deletes
  into the BST.
- **`CustomerRegistry`** — manages customer records via `CustomerHashTable` for fast
  access, with JDBC persistence. Exposes CRUD and rental-history retrieval.
- **`RentalService`** — handles rent/return flow, availability checks, overdue
  detection, and prioritization via `RentalPriorityQueue`. Generates reports
  (rentals, overdue, customer activity).

### Database (`database/`)
- **`DatabaseConnection`** — single static JDBC connection provider using the
  pattern shown in `starter_architecture_guide.md` (`jdbc:mysql://localhost:3306/movie_rental`,
  `root`, empty password by default; configurable constants).

### GUI (`gui/`)
- **`LoginFrame`** — admin authentication against the `admin` table.
- **`MainFrame`** — dashboard with `setLayout(null)`, navigation to panels.
- **`MoviePanel`** — movie listings, Add/Update/Delete forms, search by
  title/genre/year/rating, "Sort (BST)" using `MovieBST` in-order traversal.
- **`CustomerPanel`** — customer CRUD, contact view, rental history viewer.
- **`RentalPanel`** — rent/return actions, availability check, overdue/next-due
  queue view from `RentalPriorityQueue`, and report generation.

### Entry Point
- **`Main.java`** — standalone launcher for the Swing app.

---

## 4. Recommended Project Structure (from `starter_architecture_guide.md`)

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
        └── 01_admin.sql

`database/01_admin.sql` must include `DROP TABLE IF EXISTS` in reverse-foreign-key
order and pre-seed **only** `admin` / `admin123`. No other rows. No
`02_customers.sql` sample-data file.

---

## 5. Starter vs. Student-Implementation Matrix (from `starter_architecture_guide.md`)

| Component | Starter Provides | Students Implement |
| :--- | :--- | :--- |
| `Movie` / `Customer` / `Rental` | Class structure | Complete properties and methods |
| BST (`MovieBST`) | Node + method skeleton | `insert`, `search`, `delete`, in-order traversal |
| Hash Table (`CustomerHashTable`) | Array + hash function | Collision handling (separate chaining), CRUD, search |
| Priority Queue (`RentalPriorityQueue`) | `PriorityQueue<Rental>` declaration | `addRental`, `getNextDueRental`, due-date ordering |
| JDBC (`DatabaseConnection`) | Connection pattern | All CRUD SQL queries |
| GUI | Basic frame/panel pattern | Complete GUI with `setLayout(null)` and `setBounds` |
| Movie Management | Method skeletons | Add/update/delete/search |
| Customer Management | Method skeletons | Add/update/delete/search |
| Rental | Method skeletons | Rent/return/availability |
| Reports | Placeholder | Implement rental/overdue/customer-activity reports |
| Exception Handling | Basic pattern | Validation and error handling |

---

## 6. DSA Design Requirements (Anchor Project)

### `MovieBST` (Binary Search Tree)
- Node holds a `Movie` (key = title by default; document if keyed on genre).
- Required operations: `insert(Movie)`, `search(String title)`,
  `delete(int movieId)`, `inOrder()` returning `List<Movie>`.
- Used by `MoviePanel`'s sort/search actions and by `MovieCatalog.searchMovie`.
- Must be hand-written; do **not** wrap `java.util.TreeMap`/`TreeSet`.

### `CustomerHashTable` (Separate Chaining)
- Custom hash table with bucket array and chained collision handling.
- Required operations: `put(Customer)`, `get(String id)`, `remove(String id)`,
  `values()` for report iteration.
- Used by `CustomerRegistry` and `CustomerPanel` for O(1)-average lookup by ID.
- Must be hand-written; do **not** wrap `java.util.HashMap`.

### `RentalPriorityQueue` (Min-Heap on Due Date)
- Backed by `PriorityQueue<Rental>` (per starter skeleton) **or** a hand-written
  heap. The professor's skeleton accepts `java.util.PriorityQueue`, so either is
  allowed provided the ordering is on `dueDate` and the operations are implemented.
- Required operations: `addRental(Rental)`, `getNextDueRental()`, and a way to
  iterate the queue for overdue reports (e.g., copy-then-poll or expose the
  underlying queue read-only to the service layer).
- Used by `RentalService` for overdue detection and by `RentalPanel` for the
  "next due" display.

### Sorting & Searching
- Search: BST (title/genre) + hash (customer ID).
- Sort: BST in-order traversal for movie sorting. Additional report sorting, if
  needed, is done in `service/` using standard JDK APIs or a hand-written
  algorithm consistent with the lesson pattern. No external sorting libraries.

---

## 7. Database Schema (Anchor Project, from `starter_architecture_guide.md`)

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

    INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

**Seeding rule:** Only the admin row is permitted. No sample movies, customers,
or rentals may be pre-inserted; all operational data must be entered through
the GUI during the presentation.

---

## 8. GUI Standards (from `starter_architecture_guide.md` + `project_roster_and_topics.md`)

- Every `JFrame` and `JPanel` uses `setLayout(null)` and explicit `setBounds`.
- Every interactive class `implements ActionListener` and overrides
  `actionPerformed(ActionEvent e)`; listeners wired with `addActionListener(this)`.
- No NetBeans GUI Builder, IntelliJ GUI Designer, or WindowBuilder artifacts.
- Frames sized generously (e.g., `960x720` or similar) to avoid squished controls.
- Each primary entity panel includes an **"Update Selected"** button; selecting
  a table row auto-fills the form fields.
- Report views (rentals, overdue, customer activity) reachable from
  `RentalPanel` and/or `MainFrame`.

---

## 9. Decoupled DSA (Java Reflection)

Decoupling the DSA layer via Java Reflection is **not** a strict baseline requirement of
`project_roster_and_topics.md` or `starter_architecture_guide.md`. However, **if explicitly stated and wanted by the user
(the group)**, Java Reflection **must** be implemented in the `gui/` and `service/` packages 
to decouple them from the `ds/` package. 

When this pattern is adopted, it must adhere to the following standards:
- **Graceful Fallback:** If the DSA classes in `ds/` are missing (e.g., during evaluation), 
  the `service/` and `gui/` layers must gracefully fall back to default JDBC SQL sorting 
  and insertion operations without throwing compile-time errors.
- **Strict Reflection Use:** Use only standard JDK reflection APIs (`Class.forName(...)`, 
  `getMethod(...)`, `invoke(...)`). 
- **No External Dependencies:** Introduce no external libraries or GUI-builder dependencies.
- **Scope:** Reflection bridging must be contained within the `service/` and `gui/` 
  packages; the `ds/` package itself remains pure standard Java with no knowledge of 
  the database or UI.

If the user does not explicitly request Reflection, the simplest compliant path remains
direct method calls: GUI → `service/` → `ds/` → `database/`.

---

## 10. Verification & Defense Checklist

Before submitting, confirm:

1. `mvn compile` succeeds with **zero errors** (Java 21+).
2. `mvn test` passes; required unit tests for the BST, hash table, and priority
   queue are green. No skipped tests for required functionality.
3. `database/01_admin.sql` drops tables in reverse-FK order and seeds **only**
   `admin` / `admin123`. No other rows. No `02_customers.sql` file.
4. Every GUI frame/panel uses `setLayout(null)` + `setBounds(...)`; no
   drag-and-drop builder artifacts.
5. Every primary entity panel includes an **"Update Selected"** feature with
   auto-fill.
6. `MovieBST`, `CustomerHashTable`, and `RentalPriorityQueue` are hand-written
   (no wrapping of `java.util.TreeMap`/`HashMap` in place of the custom
   structures; `PriorityQueue<Rental>` use is permitted per the starter skeleton).
7. The three DSA structures are demonstrably wired into the matching services:
   `MovieCatalog` ↔ `MovieBST`; `CustomerRegistry` ↔ `CustomerHashTable`;
   `RentalService` ↔ `RentalPriorityQueue`.
8. All data (movies, customers, rentals) can be **added, updated, and deleted
   live via the GUI** with no pre-seeded operational rows.
9. Exception handling covers duplicate entries, invalid inputs, and JDBC
   connection failures.
10. `pom.xml` declares `mysql-connector-j` as the only external runtime
    dependency.

---

## 11. Project Adaptation Catalogs (Items 1–8 & 10)

Each item below is a **domain adaptation** of the anchor project (§1–§10). Every
item **inherits all rules from §1** verbatim. The DSA structures named in each
item are the professor's assigned DSA for that group and **must be hand-written
in `ds/`** (with the narrow exception of `java.util.PriorityQueue` as a backing
store for the priority-queue assignment, per the starter skeleton). No
pre-seeded operational data is allowed for any item — only the admin row.

### Item 1 — Inventory Management System
**Entities (`model/`):** `Product` (id, name, quantity, supplierId, price),
`Supplier` (id, name, contactInfo), `StockOrder` (orderId, productId, quantity,
totalCost, orderDate).

**Required DSA (`ds/`):**
- `InventoryLinkedList` — hand-written singly or doubly linked list for dynamic
  inventory tracking (no `java.util.LinkedList`).
- `ProductBST` — binary search tree keyed on product category or price; hand-written
  `insert`/`search`/`delete`/in-order.
- `SupplierHashTable` — separate-chaining hash table for supplier lookup by ID/name.

**Services (`service/`):** `InventoryService`, `SupplierRegistryService`,
`StockReportService`.

**GUI panels (`gui/`):** `ProductPanel` (CRUD + "Update Selected" + "Reset & Sort
(BST)"), `SupplierPanel` (CRUD), `OrderReportPanel` (low-stock/restock reports).

**Strict compliance notes:** No pre-seeded products/suppliers/orders. Admin seed
only. All `setLayout(null)`. Panels `implements ActionListener`.

---

### Item 2 — Online Food Delivery System
**Entities (`model/`):** `Restaurant` (id, name, cuisine, location, rating),
`Customer` (id, name, address, phone), `Order` (orderId, customerId, restaurantId,
totalAmount, status, deliveryTimeMinutes).

**Required DSA (`ds/`):**
- `RestaurantHashTable` — separate-chaining hash table indexing restaurants by
  location/cuisine.
- `OrderPriorityQueue` — min-heap on delivery time / urgency. May use
  `java.util.PriorityQueue` as backing store per starter convention.
- `DeliveryRouteGraph` — hand-written adjacency-list graph for route distances
  (no external graph library).

**Services (`service/`):** `RestaurantCatalogService`, `OrderDispatchService`,
`DeliveryAnalyticsService`.

**GUI panels (`gui/`):** `RestaurantPanel` (CRUD + "Update Selected" + cuisine
search), `OrderPanel` (place orders, "Dispatch Next Urgent Order" from
`OrderPriorityQueue`, status updates), `CustomerPanel` (CRUD + delivery history).

**Strict compliance notes:** No pre-seeded restaurants/customers/orders. Admin
seed only. Graph must be hand-written in `ds/`.

---

### Item 3 — School Management System
**Entities (`model/`):** `Student` (id, name, email, yearLevel),
`Course` (courseId, courseName, credits, department),
`Grade` (gradeId, studentId, courseId, score, letterGrade).

**Required DSA (`ds/`):**
- `StudentArrayList` — hand-written resizable array (no `java.util.ArrayList`).
- `CourseBST` — binary search tree keyed on course code.
- `GradeQuickSort` — hand-written QuickSort with ascending/descending toggle
  (no `java.util.Arrays.sort`/`Collections.sort` substitution).

**Services (`service/`):** `StudentRegistryService`, `CurriculumService`,
`TranscriptService`.

**GUI panels (`gui/`):** `StudentPanel` (CRUD + "Update Selected" + name search),
`CoursePanel` (CRUD + BST ordering), `GradePanel` (score entry, transcript,
QuickSort ranking).

**Strict compliance notes:** No pre-seeded students/courses/grades. Admin seed
only.

---

### Item 4 — Hospital Management System
**Entities (`model/`):** `Patient` (id, name, age, medicalHistory, triageLevel),
`Doctor` (id, name, specialty, department),
`Appointment` (appointmentId, patientId, doctorId, date, urgency, status).

**Required DSA (`ds/`):**
- `PatientBST` — binary search tree keyed on patient ID or admission date.
- `AppointmentPriorityQueue` — max-heap on triage urgency. May use
  `java.util.PriorityQueue` as backing store.
- `DoctorHashTable` — separate-chaining hash table for specialty lookups.

**Services (`service/`):** `PatientAdmissionService`, `AppointmentService`,
`MedicalReportService`.

**GUI panels (`gui/`):** `PatientPanel` (CRUD + "Update Selected" + BST search),
`DoctorPanel` (CRUD), `AppointmentPanel` (triage queue, "Admit Next Emergency
Patient", printable clinical summary).

**Strict compliance notes:** No pre-seeded patients/doctors/appointments. Admin
seed only.

---

### Item 5 — E-commerce Platform
**Entities (`model/`):** `Product` (id, name, price, category, rating),
`User` (id, name, email, role: ADMIN/CUSTOMER),
`Order` (orderId, userId, productSummary, totalAmount, date).

**Required DSA (`ds/`):**
- `UserSessionHashTable` — separate-chaining hash table for active user sessions.
- `ProductQuickSort` — hand-written QuickSort toggleable by price and by rating.
- `ProductBST` — binary search tree for title and price-range lookups.

**Services (`service/`):** `ProductCatalogService`, `UserAuthService`,
`OrderCheckoutService`.

**GUI panels (`gui/`):** `CatalogPanel` (toggleable QuickSort by price/rating),
`CartCheckoutPanel` (cart, checkout, printable receipt), `AdminProductPanel`
(CRUD + "Update Selected").

**Strict compliance notes:** Only admin seed. No pre-seeded products/users/
orders. Role-based access per `project_roster_and_topics.md` pattern (admin login only).

---

### Item 6 — Event Management System
**Entities (`model/`):** `Event` (eventId, name, date, location, capacity),
`Attendee` (id, name, email, vipStatus),
`Registration` (registrationId, eventId, attendeeId, ticketPrice, checkInStatus).

**Required DSA (`ds/`):**
- `AttendeeLinkedList` — hand-written linked list for registered attendees.
- `EventBST` — binary search tree keyed chronologically on event date.
- `WaitlistPriorityQueue` — priority queue on registration timestamp + VIP tier.
  May use `java.util.PriorityQueue` as backing store.

**Services (`service/`):** `EventHostingService`, `RegistrationService`,
`EventAnalyticsService`.

**GUI panels (`gui/`):** `EventPanel` (CRUD + "Update Selected" + "Reset & Sort
(BST)"), `AttendeePanel` (registration + check-in toggle + directory),
`WaitlistPanel` (VIP queue viewer + promote).

**Strict compliance notes:** No pre-seeded events/attendees/registrations.
Admin seed only.

---

### Item 7 — Fitness Tracker Application
**Entities (`model/`):** `Workout` (workoutId, exerciseName, durationMinutes,
caloriesBurned, date), `Meal` (mealId, foodName, calories, proteinGrams,
mealType), `FitnessUser` (id, name, targetCalories, weightKg).

**Required DSA (`ds/`):**
- `WorkoutDynamicArray` — hand-written resizable array (no `java.util.ArrayList`).
- `WorkoutBST` — binary search tree keyed chronologically on workout date/time.
- `NutritionQuickSort` — hand-written QuickSort by calorie density or protein
  content (ascending/descending toggle).

**Services (`service/`):** `WorkoutTrackingService`, `NutritionService`,
`FitnessProgressService`.

**GUI panels (`gui/`):** `WorkoutPanel` (CRUD + "Update Selected" + "Reset & Sort
(BST)"), `MealPanel` (CRUD + QuickSort buttons), `ProgressReportPanel`
(net-calorie summary + printable log).

**Strict compliance notes:** No pre-seeded workouts/meals/users. Admin seed only.

---

### Item 8 — Online Voting System
**Entities (`model/`):** `Candidate` (candidateId, name, party, position,
votesReceived), `Voter` (voterId, name, hasVoted, district),
`VoteAudit` (auditId, voterId, candidateId, timestamp).

**Required DSA (`ds/`):**
- `VoterHashTable` — separate-chaining hash table for O(1) voter verification
  that strictly prevents double voting.
- `CandidateQuickSort` — hand-written QuickSort ranking candidates by vote
  tally, descending.
- `DistrictGraph` — hand-written adjacency-list graph modeling election
  districts and ballot allocations.

**Services (`service/`):** `VoterRegistryService`, `BallotCastingService`,
`ElectionAnalyticsService`.

**GUI panels (`gui/`):** `BallotPanel` (voter login + cast ballot + instant
lock), `CandidatePanel` (admin CRUD + "Update Selected" + position filter),
`ElectionResultsPanel` (dynamic leaderboard via `CandidateQuickSort` + printable
certificate).

**Strict compliance notes:** No pre-seeded voters/candidates/votes. Admin seed
only. Graph must be hand-written in `ds/`.

---

### Item 9 — Travel Booking System
**Entities (`model/`):** `Flight` (flightId, departure, arrival, price),
`Hotel` (hotelId, name, location, rating), `Booking` (bookingId, userId,
flightId, hotelId).

**Required DSA (`ds/`):**
- `FlightBST` — binary search tree keyed on departure time.
- `PackageQuickSort` — hand-written QuickSort for package deals based on price and features.
- `CustomerHashTable` — separate-chaining hash table for customer records.

**Services (`service/`):** `FlightCatalogService`, `HotelRegistryService`, `BookingService`.

**GUI panels (`gui/`):** `FlightPanel` (CRUD + BST sort), `HotelPanel` (CRUD),
`BookingPanel` (itinerary management, QuickSort packages, invoice generation).

**Strict compliance notes:** No pre-seeded flights/hotels/bookings. Admin seed only.

---

### Item 10 — Real Estate Management System
**Entities (`model/`):** `Property` (propertyId, title, location, type:
Condo/Villa/House, price, status), `Client` (clientId, name, contactInfo,
budget), `PropertyTransaction` (transactionId, clientId, propertyId,
totalAmount, date).

**Required DSA (`ds/`):**
- `PropertyBST` — binary search tree keyed on listing price for budget-range
  search and in-order price sorting.
- `ClientHashTable` — separate-chaining hash table indexing clients by ID/
  phone.
- `PropertyQuickSort` — hand-written QuickSort toggleable by price
  (ascending/descending) or valuation rating.

**Services (`service/`):** `PropertyCatalogService`, `ClientRegistryService`,
`TransactionService`.

**GUI panels (`gui/`):** `PropertyPanel` (CRUD + "Update Selected" + "Reset &
Sort (BST)" + location search), `ClientPanel` (CRUD + budget lookup),
`TransactionPanel` (bundle matching, checkout, printable deed invoice,
executive sales report).

**Strict compliance notes:** No pre-seeded properties/clients/transactions.
Admin seed only. Location search is a linear filter or hash-based lookup —
**not** a second BST keyed on location (the spec's `PropertyBST` is price-keyed).

---

## 12. Cross-Item Compliance Matrix

Every item in §11 inherits the rules in §1. The table below confirms where the
non-negotiable rules apply and where per-item DSA substitutions are **not**
permitted.

| Rule | All Items 1–8 & 10 |
| :--- | :--- |
| Zero external libraries (JDBC connector only) | ✅ Enforced |
| `setLayout(null)` + `setBounds` | ✅ Enforced |
| `implements ActionListener` + `actionPerformed` | ✅ Enforced |
| 5-tier `model/` / `ds/` / `service/` / `database/` / `gui/` | ✅ Enforced |
| Admin-only seed in `database/01_admin.sql` | ✅ Enforced |
| No `02_*.sql` sample-data files | ✅ Enforced |
| Full CRUD + "Update Selected" on every primary panel | ✅ Enforced |
| Exception handling for duplicates/invalid/DB failure | ✅ Enforced |
| Hand-written DSA in `ds/` (no `java.util.TreeMap`/`HashMap` wrappers) | ✅ Enforced |
| `java.util.PriorityQueue` permitted as backing store for the priority-queue assignment | ✅ Per starter skeleton |
| Java Reflection DSA decoupling (if explicitly requested by user) | ✅ Enforced |
| No cross-item entity mixing | ✅ Enforced |
| No pre-seeded operational data of any kind | ✅ Enforced |