# 🏨 Ocean View Resort Management System
Java JSP Servlets MySQL Maven Jakarta EE

## 📜 Table of Contents
- [System Overview](#-system-overview)
- [Technologies Used](#-technologies-used)
- [Installation](#-installation)
- [Database Setup](#-database-setup)
- [Running the Application](#-running-the-application)
- [Testing](#-testing)
- [Documentation](#-documentation)
- [Troubleshooting](#-troubleshooting)
- [Changelog](#-changelog)
- [Author](#-author)
- [License](#-license)

## 📖 System Overview
Ocean View Resort Management System is a web-based application designed to automate and streamline day-to-day resort operations.  
It helps manage reservations, billing, and staff access in a more efficient and organized way.

The system supports:

- Secure login and session management
- Reservation management
- Room availability and rate handling
- Billing and invoice generation
- Dashboard statistics and reporting
- Print-ready invoice output

The system follows a layered architecture:

- **Presentation Layer**: JSP + Bootstrap frontend
- **Controller Layer**: Java Servlets
- **Service Layer**: Business logic and validation
- **Data Layer**: MySQL with JDBC / DAO pattern

---

## 🛠 Technologies Used

- **Java JDK 21**
- **Jakarta EE / Servlet API 6.1.0**
- **Apache Tomcat 11**
- **MySQL 8.0** (via WAMP Server)
- **Maven** (build automation)
- **JUnit 5** (testing framework)
- **Mockito** (unit testing support)
- **Bootstrap 5** (UI styling)

---

## 📦 Installation

### 📋 Prerequisites

Make sure the following are installed:

- **IDE**: IntelliJ IDEA / Eclipse
- **Java**: JDK 21
- **Server**: Apache Tomcat 11
- **Database**: WAMP Server / MySQL 8.0+
- **Build Tool**: Maven

---

## 📂 Project Structure

```text
Ocean-View-Resort/
├── pom.xml
├── README.md
├── CHANGELOG.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/icbt/
│   │   │       ├── controller/
│   │   │       ├── dao/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── service/
│   │   │       └── util/
│   │   ├── resources/
│   │   └── webapp/
│   │       ├── assets/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       ├── views/
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/
└── target/