# 🚗 Parking System (Java)

📅 Created on: March 24, 2026

A simple **console-based Parking Lot Management System** built using **Core Java (OOP concepts)**.
This project simulates basic parking operations like parking and unparking vehicles.

---

## 📌 Features

* Park a vehicle
* Unpark a vehicle
* Store vehicle details:

  * Vehicle Number
  * Vehicle Type
  * Owner Name
* Slot allocation system
* Simple CLI (Command Line Interface)

---

## 🧠 Concepts Used

* Object-Oriented Programming (OOP)
* Classes & Objects
* Encapsulation (getters & setters)
* Arrays for slot management
* User input using `Scanner`
* Basic control flow (loops & switch-case)

---

## 📁 Project Structure

```
├── Main.java          // Entry point (menu-driven program)
├── ParkingLots.java   // Parking logic (park/unpark)
├── Slots.java          // Represents a parking slot
├── Vehicle.java       // Stores vehicle details
```

---

## ▶️ How It Works

1. User selects an option:

   * Park a vehicle
   * Unpark a vehicle
   * Exit

2. While parking:

   * System assigns the next available slot
   * Stores vehicle details

3. While unparking:

   * System searches vehicle by number
   * Frees the slot if found

---

## 🖥️ Sample Output

```
Welcome to the Parking System!
1. Park a vehicle
2. Unpark a vehicle
3. Exit
Please select an option: 1

Enter vehicle number: TN01AB1234
Enter vehicle type: Car
Enter owner name: Mano

Vehicle parked at slot number: 1
```

---

## ⚠️ Current Limitations

* Slots are not reused after unparking
* Data is not persisted (resets on restart)
* Only supports fixed number of slots (100)
* No validation for duplicate vehicle entries

---

## 🚀 Future Improvements

* Reuse freed parking slots
* Use `ArrayList` or `HashMap` for better management
* Add search functionality
* Store data using file/database (JDBC)
* Add vehicle type-based slot allocation
* Build REST API using Spring Boot

---

## 🛠️ Tech Stack

* Java (Core Java)
* VS Code / IntelliJ
* JDK 8+

---

## 📚 Learning Outcome

This project helps in understanding:

* Real-world application of OOP
* Basic system design thinking
* Handling state in applications
* Debugging common Java issues

---

## 🙌 Author

**Manoharan**
📅 Project Date: March 24, 2026

---

⭐ If you found this useful, consider giving it a star!
