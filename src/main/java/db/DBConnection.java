package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    public static Connection connection;
    private DBConnection() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify_store", "root", "1234");
    }
    public static Connection getConnection(){
        return connection;
    }
    public static DBConnection getInstance() throws SQLException {
        return null==instance?instance=new DBConnection():instance;
    }
}