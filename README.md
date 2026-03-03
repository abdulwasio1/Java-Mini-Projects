# Banking Transaction System
A simple console-based banking application that demonstrates core Java concepts including Object-Oriented Programming (OOP), encapsulation, and basic transaction handling.

## 📚 Features
1. Create Savings and Checking accounts
2. Deposit and withdraw money
3. Transfer funds between accounts
4. Apply interest based on account type
5. Check low balance warnings
6. View transaction history
7. Account summaries with fee calculations

## 🏗️ Key Concepts Demonstrated
1. Classes and Objects
2. Encapsulation (private fields with public methods)
3. Method overloading
4. Array-based transaction history
5. Input validation
6. Basic banking logic (minimum balance, fees)

More know : [https://medium.com/p/a0c4f59d8e25]

# Library Management System - Java Console App
A console-based Library Management System built in Java demonstrating OOP concepts and data structures.

## 📚 Features
1. Create Books - Add books with unique IDs
2. Borrow/Return Books - Issue and process returns
3. Fine Calculation - $0.50/day after 14-day grace period
4. Extend Period - Add extra days to borrowed books
5. Overdue Check - Check if a book is overdue
6. View All Books - Display complete catalog

## 🏗️ Architecture
├── Library Class - Business logic (borrow, return, fine, etc.)
└── Task1 Class - Application controller with circular buffer storage
Key Concepts
OOP (Encapsulation, Constructors)
Circular Buffer - Fixed-size collection management
Static Members
Input Validation
Menu-Driven Interface

## 💻 Menu Options
1. Create a Book
2. Borrow Book
3. Extend Borrow Days
4. Check if Overdue
5. Calculate Fine
6. Return Book
7. Show All Books
8. Exit

## 🔧 Circular Buffer
books[rear] = new Library(bookName, bookId);

rear = (rear + 1) % capacity;  // Wraps around when full

## 🎯 Perfect For
Java beginners learning OOP
Understanding data structures
Practice with user input handling

More know: [https://medium.com/p/5f7471a41972]

# 🏃‍♂️ Fitness Tracker
A Java console app to track daily steps, workouts, calories, and weekly progress toward fitness goals.

## ✨ Features
👣 Step Tracking – Add daily steps
💪 Workout Logger – Log minutes & type (Running/Walking/Cycling/Swimming) → auto-calculates calories
📅 Weekly Steps – Update steps for each day (0-6)
🎯 Goal Setting – Set step & calorie goals
📊 Progress % – See daily goal completion
🏆 Goal Check – Know when you've achieved targets
📈 Weekly Stats – Average steps + best day
👥 Compare Users – See who's ahead
🔄 Reset Daily – Start fresh each day
📋 Full Report – View all stats in one place

## 🧱 Classes
FitnessTracker – Core logic (steps, calories, goals, reports)
Task6 – Main menu & user interaction

## 🧮 Calories/min
🏃 Running – 10
🚶 Walking – 5
🚴 Cycling – 8
🏊 Swimming – 12
🎯 Other – 6

## 🖥️ Menu Options
1. Add Steps
2. Record Workout
3. Update Weekly Steps
4. View Daily Progress
5. Check Goal Achievement
6. View Fitness Report
7. Compare User Progress
8. Reset Daily Stats
9. Exit

## ⚠️ Validation
❌ No negative steps/minutes
📆 Day index must be 0–6
🧾 Clear error messages

### More projects will be added soon.
