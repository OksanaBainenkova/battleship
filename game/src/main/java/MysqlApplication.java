import java.sql.*;

public class MysqlApplication {
    public static Connection createConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/battleship_score", "root", "root");
        return connection;
    }
}
