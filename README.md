# Employee Management System

A Java EE web application for managing employee records using Jakarta EE, PrimeFaces, and MySQL.

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- MySQL 8.0 or higher
- Payara Server 6.2025.4 or higher

## Setup Instructions

### 1. Install Payara Server

1. Download Payara Server 6.2025.4 from [Payara Downloads](https://www.payara.fish/downloads/payara-platform-community-edition/)
2. Extract the downloaded file to your desired location
3. Add Payara's `bin` directory to your system's PATH environment variable

### 2. Install MySQL Connector

1. Download the MySQL Connector/J from [MySQL Downloads](https://dev.mysql.com/downloads/connector/j/)
2. Copy the downloaded JAR file (e.g., `mysql-connector-j-8.0.33.jar`) to:
   ```
   [Payara_Home]/glassfish/lib/
   ```

### 3. Configure MySQL Database

1. Start MySQL server
2. Create a new database and user:
   ```sql
   CREATE DATABASE employeedb;
   CREATE USER 'root'@'localhost' IDENTIFIED BY 'fant4stic';
   GRANT ALL PRIVILEGES ON employeedb.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```
3. Create the employees table:
   ```sql
   USE employeedb;
   CREATE TABLE employees (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       first_name VARCHAR(50) NOT NULL,
       last_name VARCHAR(50) NOT NULL,
       email VARCHAR(100) NOT NULL,
       department VARCHAR(50) NOT NULL,
       salary DECIMAL(10,2) NOT NULL
   );
   ```

### 4. Build the Application

1. Clone this repository
2. Navigate to the project directory
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### 5. Deploy and Run the Application

1. Start Payara Server:
   ```bash
   cd [Payara_Home]/bin
   ./asadmin start-domain
   ```

2. Deploy the application:
   ```bash
   ./asadmin deploy [path_to_war_file]/EmployeeApp-1.0-SNAPSHOT.war
   ```

3. Access the application at:
   ```
   http://localhost:8080/EmployeeApp/index.xhtml
   ```

## Application Features

- Create new employee records
- View all employees in a paginated table
- Edit existing employee information
- Delete employees with confirmation dialog
- Form validation for required fields
- Email format validation
- Salary must be positive

## Project Structure

```
EmployeeApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/muchiri/
│   │   │       ├── bean/
│   │   │       │   └── EmployeeBean.java
│   │   │       ├── dao/
│   │   │       │   └── EmployeeDAO.java
│   │   │       ├── model/
│   │   │       │   └── Employee.java
│   │   │       └── util/
│   │   │           └── DatabaseUtil.java
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── beans.xml
│   │       │   ├── faces-config.xml
│   │       │   └── web.xml
│   │       ├── create.xhtml
│   │       ├── edit.xhtml
│   │       └── index.xhtml
│   └── test/
├── pom.xml
└── README.md
```

## Dependencies

- Jakarta EE 10.0.0
- PrimeFaces 13.0.0
- MySQL Connector/J 8.0.33

## Troubleshooting

1. If the application fails to connect to the database:
   - Verify MySQL server is running
   - Check database credentials in `DatabaseUtil.java`
   - Ensure MySQL Connector is properly installed in Payara

2. If the application fails to deploy:
   - Check Payara server logs
   - Verify all dependencies are correctly specified in `pom.xml`
   - Ensure Java version matches the project requirements

3. If the application runs but features don't work:
   - Clear browser cache
   - Check browser console for JavaScript errors
   - Verify Payara server logs for any exceptions

## License

This project is licensed under the MIT License - see the LICENSE file for details. 