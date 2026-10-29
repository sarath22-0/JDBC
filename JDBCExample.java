import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCExample {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    public static void main(String[] args) {
        String sql = "SELECT id, name, email FROM users WHERE status = ?";

        // Try-with-resources ensures connections and statements are closed automatically
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter value (prevents SQL injection)
            pstmt.setString(1, "ACTIVE");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Databse conected......");
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}