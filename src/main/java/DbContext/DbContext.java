package DbContext;


import java.sql.*;

public class DbContext {
    static String url = "jdbc:mariadb://localhost/rental_car_v2";
    static String username = "root";
    //static String motDePasse = "";
    static Connection conn;


    public DbContext() throws ClassNotFoundException {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, "");
            System.out.println("Connected to MariaDB database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet Execute(String str) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(str);
        return preparedStatement.executeQuery();
    }

}
