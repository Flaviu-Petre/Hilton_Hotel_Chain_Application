package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    String _url = "jdbc:postgresql://localhost:5432/HotelDB";
    String _username = "";
    String _password = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(_url, _username, _password);
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
