# Documentation Template Specification (`docs_template_spec.md`)

This document dictates the required structure and content formatting for the `README.md` and `CHANGELOG.md` files. Any LLM or AI Agent completing a feature must generate or update these files according to these templates.

[![View Api Docs](https://img.shields.io/badge/View-Interactive%20Guide-blue?style=for-the-badge)](https://rinka-sei.github.io/dsa_travel_booking_system)

Hosted interactive API documentation and architectural guide: [https://rinka-sei.github.io/dsa_travel_booking_system](https://rinka-sei.github.io/dsa_travel_booking_system)

---

## 1. `README.md` Template

Every project generated MUST include a `README.md` following this exact structure:

    # [Project Name] (Java) — [Group Name or Number] Framework

    A brief 1-2 sentence summary of the system, its purpose, and the core DSA implemented.

    ---

    ## 🚀 Key Functional Features
    - **[Feature 1]:** Brief description (e.g., "Complete CRUD for all entities").
    - **[Feature 2]:** Brief description (e.g., "Priority Queue for triage/urgent sorting").
    - **[Feature 3]:** Brief description (e.g., "Java Reflection DSA decoupling").
    - **[Feature 4]:** Brief description (e.g., "Enterprise JavaDocs and JUnit 5 testing").

    ---

    ## 📐 Modular Package Structure
    [Insert the visual ASCII/Unicode directory tree here, matching the 5-tier architecture]

    ---

    ## 📜 Strict Professor Rule Compliance
    1. **Absolute Layout Positioning (`setLayout(null)`):** Every frame and panel uses explicit coordinate layout (`setLayout(null)`) and manual component placement via `setBounds(x, y, width, height)`. No visual GUI builders are used.
    2. **Zero External Libraries (JDBC Connector Only):** Relies solely on standard JDK 21+ APIs and `mysql-connector-j` as the sole external dependency.
    3. **GUI Live-Seeding Rule:** Database setup scripts are located in the root `database/` directory (`database/01_admin.sql` pre-seeding default admin `admin` / `admin123`). No operational data is pre-seeded.

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

        # Run JUnit 5 unit test suite
        mvn test

---

## 2. `CHANGELOG.md` Template

Every agent commit MUST append a new entry to the top of `CHANGELOG.md` (under the main title) in the following format:

    # Project Changelog

    All notable changes, milestones, and documentation updates for the repository are documented chronologically in this file.

    ---

    ## [YYYY-MM-DD] - [Feature Scope] (e.g., Core DSA, GUI Overhaul, Bug Fix)

    ### Added
    - **[Component/Class Name]:** Brief description of what was added (e.g., "Implemented `MovieBST.java` for in-order sorting").
    - **[Component/Class Name]:** Brief description (e.g., "Added JUnit 5 tests for `CustomerHashTable` collision handling").
    - **Documentation:** Updated `README.md` to reflect new architecture.

    ### Changed
    - **[Component/Class Name]:** Brief description of refactored or modified code.
    - **[Configuration]:** Updated `pom.xml` to include Java 21 compiler settings.

    ### Fixed
    - **[Component/Class Name]:** Brief description of bug resolved.