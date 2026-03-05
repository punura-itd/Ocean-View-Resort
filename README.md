# Ocean View Resort Management System

A JSP/Servlet-based web application for managing hotel reservations and billing for Ocean View Resort (Galle).

## Features
- User Authentication (Login + session)
- Reservation Management (Add/View)
- Auto Reservation Number (RES-0001 format)
- Billing Calculation (nights × DB room rates)
- MySQL (WAMP) database integration via JDBC

## Tech Stack
- Java (JSP/Servlet)
- Apache Tomcat
- MySQL (WAMP)
- JDBC
- Bootstrap UI

## Setup
### 1. Database
1. Start WAMP server
2. Create a MySQL database (example: `ocean_view_resort`)
3. Run your SQL scripts to create tables (`reservations`, `room_rates`, etc.)

### 2. Configure DB Connection
Update:
`src/main/java/com/icbt/util/DBConnection.java`

```java
public static final String URL = "jdbc:mysql://localhost:3306/<db_name>";
public static final String USER = "root";
public static final String PASSWORD = "";