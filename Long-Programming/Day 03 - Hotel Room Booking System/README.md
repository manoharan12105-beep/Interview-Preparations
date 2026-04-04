🏨 Hotel Room Booking System

📌 Project Overview

The Hotel Room Booking System is a console-based backend application built using Java, Spring Boot, and MySQL. It allows users to register, login, book hotel rooms, and manage bookings, while admins can manage rooms and monitor bookings.

This project demonstrates core backend development concepts, including:

- Object-Oriented Programming (OOP)
- Spring Boot architecture
- JPA & Hibernate
- Database integration
- Console-based interaction system

---

🚀 Features

👤 User Module

- Register new user
- Login authentication
- View available rooms
- Book rooms
- View booking history
- Cancel bookings

👑 Admin Module

- Add new rooms
- View all rooms
- Delete rooms
- View all bookings

---

🧱 Tech Stack

- Language: Java
- Framework: Spring Boot
- Database: MySQL
- ORM: Hibernate (JPA)
- Tools: Maven, VS Code

---

🗄️ Database Design

User Table

- userId (Primary Key)
- name
- email
- password
- role

Room Table

- roomId (Primary Key)
- roomType
- price
- available

Booking Table

- bookingId (Primary Key)
- userId (Foreign Key)
- roomId (Foreign Key)
- checkIn
- checkOut
- totalPrice
- status

---

⚙️ Core Functionalities

🔐 Authentication

- Validates user using email & password
- Returns logged-in user object

🛏 Room Management

- Add/Delete/View rooms
- Track room availability

📅 Booking System

- Book rooms with check-in & check-out dates
- Calculate total price using:

long days = ChronoUnit.DAYS.between(checkIn, checkOut);

❌ Cancellation

- Update booking status to "CANCELLED"

---

🧠 Key Concepts Used

- Dependency Injection ("@Autowired")
- Entity Mapping ("@Entity")
- Repository Layer (Spring Data JPA)
- Service Layer (Business Logic)
- Console-based UI flow
- Exception handling basics

---

▶️ How to Run

1. Clone the repository:

git clone https://github.com/manoharan12105-beep/Interview-Preparations.git

2. Navigate to project:

cd Long-Programming/Day\ 03\ -\ Hotel\ Room\ Booking\ System

3. Configure MySQL database in "application.properties"

4. Run the application:

mvn spring-boot:run

---

📌 Project Flow

Register → Login → User/Admin Menu → Perform Operations → Logout

---

🚧 Future Improvements

- JWT Authentication
- REST API conversion
- Frontend integration (React)
- Payment integration
- Date-based room availability
- Role-based authorization

---

📅 Date

Created on: April 4, 2026

---

🙌 Author

Manoharan

---

⭐ Notes

This project is part of a long programming practice series and is designed to strengthen backend development skills using Java and Spring Boot. It reflects real-world system design concepts like user management, booking logic, and admin control.
