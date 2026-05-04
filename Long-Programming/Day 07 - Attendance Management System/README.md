# 📊 Attendance Management System

A full-stack Attendance Management System built using Spring Boot that helps manage students, track attendance, and maintain records efficiently.

---

## 🚀 Features

* 👨‍🎓 Student Management (Add, Update, Delete)
* 🧑‍💼 Role-based dashboards (Admin & Student)
* 📅 Daily Attendance Tracking (time-window based)
* 📊 Monthly Attendance Reports & Analytics
* 🗓️ Holiday Calendar support (excluded from attendance percentage)
* 🔐 Authentication (Email/Password + Google OAuth2)
* ⚡ Fast and scalable Spring Boot architecture

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot (Java 21)
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Security:** Spring Security + OAuth2 Client
* **Build Tool:** Maven
* **Server:** Embedded Tomcat
* **Frontend:** HTML, CSS, JavaScript + Chart.js

---

## 📁 Project Structure

```text
Attendance-Management-System-Final/
 ├── README.md
 └── attendence-management-system/
      ├── pom.xml
      └── src/
          ├── main/
          │   ├── java/
          │   │   └── me/mano/attendence_management_system/
          │   │        ├── controller/
          │   │        ├── service/
          │   │        ├── repo/
          │   │        ├── entity/
          │   │        ├── security/
          │   │        └── AttendenceManagementSystemApplication.java
          │   └── resources/
          │        ├── application.properties
          │        └── static/
          │             ├── index.html
          │             ├── admin.html
          │             ├── student.html
          │             ├── css/
          │             └── js/
          └── test/
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/manoharan12105-beep/Interview-Preparations.git
cd "Interview-Preparations/Long-Programming/Day 07 - Attendance Management System/attendence-management-system"
```

---

### 2️⃣ Configure Database

This project reads DB credentials from `.env` values.

Create file:

```text
attendence-management-system/src/.env
```

Add your database values:

```properties
DATA_SOURCE_URL=jdbc:mysql://<db-host>:<db-port>/<db-name>
DATA_USERNAME=<db-username>
DATA_PASSWORD=<db-password>

OAUTH_CLIENT_ID=<google-client-id>
OAUTH_CLIENT_SECRET=<google-client-secret>
```

---

### 3️⃣ Run the Application

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

or run the main class:

```text
AttendenceManagementSystemApplication.java
```

---

## 🌐 API Endpoints

### 👤 User APIs

| Method | Endpoint         | Description             |
| ------ | ---------------- | ----------------------- |
| POST   | /api/users/login | Email/Password login    |
| GET    | /api/users/me    | Get current logged user |

### 👨‍🎓 Student APIs

| Method | Endpoint                 | Description         |
| ------ | ------------------------ | ------------------- |
| GET    | /api/students            | Get all students    |
| POST   | /api/students            | Add new student     |
| PUT    | /api/students/{sprno}    | Update student      |
| DELETE | /api/students/{sprno}    | Delete student      |
| GET    | /api/students/search?q=  | Search students     |

### 📅 Attendance APIs

| Method | Endpoint                                                | Description              |
| ------ | ------------------------------------------------------- | ------------------------ |
| POST   | /api/attendance/mark                                    | Mark attendance          |
| GET    | /api/attendance/daily?date=YYYY-MM-DD                   | Daily attendance report  |
| GET    | /api/attendance/weekly?start=YYYY-MM-DD&end=YYYY-MM-DD | Weekly attendance report |
| GET    | /api/attendance/monthly-report?year=YYYY&month=M        | Monthly report table     |
| GET    | /api/attendance/monthly-graph?year=YYYY&month=M         | Monthly graph data       |
| GET    | /api/attendance/my-stats?sprno=...                      | Student personal stats   |
| GET    | /api/attendance/my-history?sprno=...&limit=5            | Student recent activity  |
| GET    | /api/attendance/class-stats                             | Class-level statistics   |

### ⚙️ Settings APIs

| Method | Endpoint                                 | Description                   |
| ------ | ---------------------------------------- | ----------------------------- |
| GET    | /api/settings/attendance-time            | Get attendance time window    |
| POST   | /api/settings/attendance-time            | Update attendance time window |
| GET    | /api/settings/holidays?year=YYYY&month=M | Get holidays of month         |
| POST   | /api/settings/holidays/{date}            | Add holiday                   |
| DELETE | /api/settings/holidays/{date}            | Remove holiday                |

---

## 🧠 How It Works

* Stores student and attendance data in **MySQL**
* Uses JPA repositories for database operations
* REST controllers expose APIs used by static frontend pages
* Attendance can be marked only within configured time duration
* Holidays are excluded from monthly percentage calculations

---

## 🔑 Default Admin Login (Auto Seeded)

On first startup, the system creates:

* **Email:** `admin@example.com`
* **Password:** `Admin123_$`

> Change this after first login if deploying publicly.

---

## 📌 Future Improvements

* 🔒 Password hashing (BCrypt) for stronger security
* 🧾 Export attendance reports (PDF/Excel)
* 🔔 Notifications for low attendance
* ☁️ Docker + cloud deployment configuration

---

## 🤝 Contributing

Pull requests are welcome. For major changes, open an issue first to discuss what you'd like to improve.

---

## 👨‍💻 Author

**Manoharan**
