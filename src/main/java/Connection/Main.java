package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static final String JDBC_URL = "jdbc:h2:mem:";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "el1972";

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD) ) {
            Statement st = con.createStatement();

        }


    }
}
