import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;


public class ExpenseManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseDAO dao = new ExpenseDAO();

    public static void main(String[] args) {
        createDatabaseTable();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nExpense Management System:");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> addExpense();
                    case 2 -> editExpense();
                    case 3 -> deleteExpense();
                    case 4 -> viewExpenses();
                    case 5 -> exit = true;
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void createDatabaseTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                amount REAL,
                category TEXT,
                date TEXT,
                description TEXT
            );
            """;
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating database table: " + e.getMessage());
        }
    }

    private static void addExpense() throws SQLException {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(0, amount, category, date, description);
        dao.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    private static void editExpense() throws SQLException {
        System.out.print("Enter expense ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new category: ");
        String category = scanner.nextLine();
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(id, amount, category, date, description);
        dao.updateExpense(id, expense);
        System.out.println("Expense updated successfully!");
    }

    private static void deleteExpense() throws SQLException {
        System.out.print("Enter expense ID to delete: ");
        int id = scanner.nextInt();
        dao.deleteExpense(id);
        System.out.println("Expense deleted successfully!");
    }

    private static void viewExpenses() throws SQLException {
        List<Expense> expenses = dao.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            System.out.println("Expenses:");
            for (Expense expense : expenses) {
                System.out.printf("ID: %d, Amount: %.2f, Category: %s, Date: %s, Description: %s%n",
                        expense.getId(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getDescription());
            }
        }
    }
}
