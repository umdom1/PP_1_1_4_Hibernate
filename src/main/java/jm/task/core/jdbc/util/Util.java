package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "V5cc76ff@s";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Соединение с базой установлено");
        return conn;
    }

}
