# 🍔 Food Delivery System (Java Console Application)

## 📌 Overview

This project is a **console-based Food Delivery System** built using **Core Java**.
It simulates a real-world food ordering workflow involving **Admin, Manager, and Customer roles**.

The system allows users to:

* Register and login
* Browse restaurants
* Order food
* Track delivery status

---

## 🚀 Features

### 👤 Customer

* Register and login
* View available restaurants
* View food menu
* Place orders
* Track delivery status

### 🧑‍💼 Admin

* Add restaurants
* View all restaurants

### 🧑‍🍳 Manager

* Login using manager credentials
* Add food items
* Remove food items
* View menu
* Update delivery status

---

## 🧱 Project Structure

```
Food Delivery System
│── Main.java
│── Customer.java
│── Restaurant.java
│── Delivery.java
```

---

## ⚙️ Technologies Used

* Java (Core Java)
* OOP Concepts
* ArrayList (Collections)
* Scanner (User Input Handling)

---

## 🧠 Concepts Covered

* Object-Oriented Programming (OOP)
* Encapsulation
* Lists & Data Handling
* Menu-driven programs
* Real-world system simulation

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone <your-repo-link>
```

2. Open in VS Code / IntelliJ

3. Compile:

```bash
javac Main.java
```

4. Run:

```bash
java Main
```

---

## 🧪 Sample Flow

1. Register a user
2. Admin adds restaurants
3. Manager adds food items
4. Customer places order
5. Manager updates delivery
6. Customer checks delivery status

---

## ⚠️ Important Notes

* User must login using **User ID (not name)**
* Manager login requires correct **Manager ID & Password**
* Food removal requires **Food ID**, not name

---

## 🔮 Future Improvements

* Replace `List<List<String>>` with proper classes (Food, Order)
* Add database integration (MySQL)
* Convert to Spring Boot REST API
* Add payment system
* Add delivery partner tracking

---

## 📷 Example Output

```
====MENU====
1) Register
2) Login
3) Exit

Customer Login Successful

====Order Menu====
1) Place order
2) Delivery Status

Order ID : 1
Status : Delivered
```

---

## 👨‍💻 Author

**Manoharan**

---

## ⭐ Acknowledgement

This project is part of **Java learning and interview preparation practice**.
