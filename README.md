# SportMinder Backend
TFG proyect

The backend of this project is developed using Spring Boot, providing a robust and scalable framework for building the server-side application. This document provides an overview of the key features and components, along with setup instructions and related links.

## Table of Contents
1. [Overview](#overview)
2. [Key Features](#key-features)
    - [Booking Management](#booking-management)
    - [Data Persistence](#data-persistence)
3. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
4. [Related Repositories](#related-repositories)
5. [License](#license)

## Overview

The backend of SportMinder application is designed to provide a reliable and scalable server-side infrastructure, handling all business logic and data management for the application.

## Key Features

### Booking Management
- Handles CRUD operations for booking reservations.
- Provides endpoints to get all bookings, get booking by ID, save bookings, update bookings, and delete bookings.
- Ensures that expired bookings are updated regularly through scheduled tasks.

### Data Persistence
- Utilizes MySQL for reliable data storage.
- Managed through Spring Data JPA for efficient data access and manipulation.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- Maven installed on your machine.
- MySQL database setup.

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/luislois/SportsMinder-Backend.git
    ```
2. Navigate to the project directory:
    ```sh
    cd project-name-backend
    ```
3. Install the dependencies:
    ```sh
    mvn install
    ```

### Running the Application
1. Ensure your MySQL database is running and accessible.
2. Update the application properties file with your database credentials and other necessary configurations:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    auth0.domain=your-auth0-domain
    auth0.clientId=your-auth0-clientId
    auth0.clientSecret=your-auth0-clientSecret
    ```
3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Related Repositories
- [Frontend Repository](https://github.com/luislois/SportsMinder-Frontend) - Handles the frontend logic and user interface.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
