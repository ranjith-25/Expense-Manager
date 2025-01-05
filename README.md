##Expense Management System

#Overview
A console-based Java application for managing personal expenses.

Features
1. Expense Management
   - Add, edit, delete, and view expenses.
   - Categorize expenses.
2. Report Generation
   - View detailed expense reports by category and date range.
3. Database Integration
   - Persistent storage using SQLite.

Technologies
- Java: Core application logic.
- SQLite: Database interaction.

#Usage
Launch the application.

Choose from the menu options:
1. Add Expense: Add a new expense.
2. Edit Expense: Modify an existing expense.
3. Delete Expense: Remove an expense from the database.
4. View All Expenses: See a list of all expenses.
5. Exit: Exit the application.

Database Schema
Field		    Type	  Description
id		      INTEGER	Primary Key (Auto Increment)
amount		  REAL	  Expense amount
category	  TEXT	  Category of the expense
date		    TEXT	  Date of the expense (YYYY-MM-DD)
description	TEXT	  Description of the expense

#Setup Instructions
1. Download the project files.
2. Open the project in Eclipse.
3. Add the SQLite JDBC driver (`sqlite-jdbc-3.41.2.jar`) to the build path.
4. Run the `ExpenseManager` class to test the application.
5. Export the project as a Runnable JAR:
   - Right-click on project > Export > Runnable JAR file.
6. Run the application:
   java -jar ExpenseManager.jar

