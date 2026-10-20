import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("Set the DB_PASSWORD environment variable before running the app.");
        }

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                "root",
                password)) {
            System.out.println("Database Connected!");

            int nextId;
            try (Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM student")) {
                rs.next();
                nextId = rs.getInt(1);
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO student (id, name, marks, grade, city) VALUES (?, ?, ?, ?, ?)")) {
                ps.setInt(1, nextId);
                ps.setString(2, "Rahul");
                ps.setInt(3, 85);
                ps.setString(4, "A");
                ps.setString(5, "Unknown");

                ps.executeUpdate();
            }

            System.out.println("Record inserted with id " + nextId);

            try (Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT id, name, marks, grade, city FROM student")) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getInt("marks") + " " +
                            rs.getString("grade") + " " +
                            rs.getString("city"));
                }
            }
        }
    }
}