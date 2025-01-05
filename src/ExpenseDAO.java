import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses(amount, category, date, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setString(2, expense.getCategory());
            pstmt.setString(3, expense.getDate());
            pstmt.setString(4, expense.getDescription());
            pstmt.executeUpdate();
        }
    }

    public void updateExpense(int id, Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET amount = ?, category = ?, date = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setString(2, expense.getCategory());
            pstmt.setString(3, expense.getDate());
            pstmt.setString(4, expense.getDescription());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }

    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                expenses.add(new Expense(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("description")
                ));
            }
        }
        return expenses;
    }
}
