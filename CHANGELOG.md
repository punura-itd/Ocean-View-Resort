# Changelog

All notable changes to the **Ocean View Resort Management System** will be documented in this file.

This project follows semantic versioning.

---

## [1.2.0] - 2026-03-05

### Added

* Invoice number generation for each bill.
* Tax calculation (10%) applied to the reservation subtotal.
* Service charge calculation (5%) applied to the reservation subtotal.
* Grand total calculation including tax and service charge.
* Professional invoice layout for generated bills.
* Print-optimized invoice using custom print CSS.
* Invoice header with resort details and billing summary.

### Improved

* Upgraded billing page UI to resemble a real hotel invoice.
* Improved bill printing to hide navigation elements and UI components.
* Enhanced readability of billing totals and summary section.

### Technical

* Extended `billDto` with invoice number, tax, service charge, and grand total.
* Updated `billingService` to compute invoice totals and generate invoice numbers.
* Updated `bill.jsp` layout and invoice display structure.

---

## [1.1.0] - 2026-03-04

### Added

* Dashboard statistics panel displaying reservation and billing information.
* Popup alert notification system for success and error messages.
* Improved UI styling with Bootstrap and custom CSS components.
* Form validation improvements for reservation inputs.

### Improved

* Login error handling with better alert messages.
* Prevented duplicate form submissions on billing and reservation forms.
* Improved responsive layout across pages.

### Documentation

* Added project README with system overview and setup instructions.
* Added CHANGELOG to track version history.

---

## [1.0.0] - 2026-03-04

### Added

* Initial release of **Ocean View Resort Management System**.
* User authentication module (login).
* Reservation management module:

    * Add reservation
    * View reservation
    * Edit reservation
    * Cancel reservation
* Reservation search by reservation number or phone number.
* Billing module:

    * Generate bill from reservation
    * Calculate total stay cost based on room rate and number of nights.
* Room rate management via database.
* MVC architecture implementation:

    * Servlet controllers
    * Service layer
    * DAO layer
    * DTO models
* MySQL database integration using JDBC.
* Basic dashboard page.
