package ma.enset.gestionconsultationbdcc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection;

    static {
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cabinet", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
            return connection;
    }

}
