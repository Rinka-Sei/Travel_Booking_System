# Project Changelog

All notable changes, milestones, and documentation updates for the repository are documented chronologically in this file.

---

## [2026-11-06] - Merge from Nightly & Admin-Only Report Access

### Added
- **`FlightBST.java`:** Adopted the binary search tree implementation for in-order flight departure sorting from the nightly repository, wired through `FlightCatalogService` via Java Reflection.
- **`PackageQuickSort.java`:** Adopted the hand-written QuickSort implementation for destination-matched package deals (price and rating direction toggles) from the nightly repository.
- **Admin-Only Report Access (`BookingPanel.java`):** The `Report` button on the `Bookings & Itinerary` tab is now visible and actionable only for accounts with the `ADMIN` role.

### Changed
- **`BookingPanel.java`:** Integrated the nightly repository's admin-only Report guard into the beta codebase without altering beta's existing panel layout.
- **`.gitignore`:** Merged nightly's ignore rules (build artifacts, generated JavaDoc) with beta's existing entries.
- **Repository Merge:** Merged `dsa_travel_booking_system-nightly`'s `main` branch into beta as a shared ancestry merge. The following were **excluded** from the merge to preserve beta's specific configuration:
  - `src/test/**` — beta retains its own testing posture (unit tests removed per beta scope)
  - `context/**` and `docs/**` — not present in beta
  - `pom.xml`, `README.md`, `CHANGELOG.md`, `LICENSE`, `nbactions.xml` — beta-specific versions retained

### Fixed
- **Merge Conflicts:** Resolved all add/add conflicts (`.gitignore`, `CHANGELOG.md`, `README.md`, `nbactions.xml`, `pom.xml`, `BookingPanel.java`) by taking nightly's version for source code (e.g., `BookingPanel.java`) and preserving beta's version for repo-specific files.

---

## [2026-11-05] - Repository Restructuring and Rebase

### Changed
- **Directory Layout:** Restructured the root directory layout to improve workspace organization and rebased commit history.

---

## [2026-11-04] - Hotel GUI Expansion and PR Integration

### Added
- **Hotel GUI Expansion:** Implemented the second phase/part 2 updates for the Hotel Graphical User Interface.
- **Package Deal GUI:** Integrated Package Deal interface components and merged pull requests #2 and #3.
- **Hotel GUI Base:** Integrated the initial Hotel GUI module via pull request #1.
- **Project Notes & Documentation:** Added initial developer notes and workspace documentation.

### Changed
- **Initial Commit:** Initialized the repository workspace for the Travel Booking System.
- **Contributor Setup:** Onboarded team contributions and initialized core user modules.