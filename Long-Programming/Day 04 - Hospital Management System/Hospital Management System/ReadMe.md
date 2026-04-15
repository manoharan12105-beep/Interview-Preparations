# 🏥 Hospital Management System (Java)

A simple **console-based Hospital Management System** built using Java.
This project simulates real-world hospital operations such as patient admission, doctor assignment, room allocation, and billing generation.

---

## 🚀 Features

* ✅ Add new patients
* ✅ Assign doctor based on illness
* ✅ Allocate rooms dynamically
* ✅ Track patient admission & discharge time
* ✅ Generate bill based on duration of stay
* ✅ Release room after discharge
* ✅ View patient details

---

## 🧠 System Design

This project follows a basic **OOP (Object-Oriented Programming)** approach:

### 📦 Classes Used

* **Main**

  * Handles menu-driven interaction
* **Patient**

  * Stores patient details
  * Tracks admit & discharge time
* **Doctor**

  * Stores doctor data
  * Assigns doctor based on problem
* **Room**

  * Manages room allocation & availability
* **Billing**

  * Calculates total bill using time-based logic

---

## ⚙️ How It Works

### 1️⃣ Add Patient

* User enters name and problem
* System:

  * Assigns doctor
  * Assigns available room
  * Stores admission time

### 2️⃣ View Patients

* Displays all admitted patients

### 3️⃣ Discharge Patient

* Records discharge time

* Calculates total bill:

  ```
  Total Bill = Hours Stayed × (Doctor Fee + Room Fee)
  ```

* Frees the allocated room

---

## 💰 Billing Logic

* Doctor fee → based on specialization (per hour)
* Room fee → based on room type (per hour)
* Minimum billing → 1 hour

---

## 🛠️ Technologies Used

* Java (Core Java)
* OOP Concepts
* Collections (ArrayList)
* Date & Time API (`LocalDateTime`, `Duration`)

---

## 📂 Project Structure

```
HospitalManagementSystem/
│
├── Main.java
├── Patient.java
├── Doctor.java
├── Room.java
├── Billing.java
```

---

## ▶️ How to Run

1. Clone the repository:

   ```
   git clone <your-repo-link>
   ```

2. Compile all files:

   ```
   javac *.java
   ```

3. Run the program:

   ```
   java Main
   ```

---

## 🔮 Future Improvements

* Convert to **Spring Boot REST API**
* Add database integration (MySQL)
* Improve UI (Web / GUI)
* Replace `List<List<String>>` with proper models
* Add authentication system

---

## 🎯 Learning Outcomes

* Strong understanding of **OOP concepts**
* Hands-on experience with **real-world system design**
* Practical use of **Java Collections & Time API**
* Introduction to **backend logic building**

---

## 🤝 Contributing

Feel free to fork this repository and improve the project!

---

## 📌 Author

**Manoharan**

---

⭐ If you found this useful, consider giving it a star!
