import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    private Connection connection;
    private Statement statement;
    private String schemaName;



    public Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        connection = DriverManager.getConnection("jdbc:h2:mem:test", "root", "root");
        return connection;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
    public Statement createStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }
    public void closeStatement() throws SQLException {
        statement.close();
    }
    public void createSchema(String schemaName) throws SQLException {
        this.schemaName = schemaName;
        String sql = "CREATE SCHEMA " + schemaName + ";";
        statement.executeUpdate(sql);
    }
    public void dropSchema() throws SQLException {
        String sql = "DROP SCHEMA " + schemaName + ";";
        statement.executeUpdate(sql);
    }
    public void useSchema() throws SQLException {
        String sql = "SET SCHEMA " + schemaName + ";";
        statement.executeUpdate(sql);
    }
    public void createTableRoles() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Roles (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "roleName VARCHAR(255)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableDirections() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Directions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "directionName VARCHAR(255)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableProjects() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Projects (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "projectName VARCHAR(255)," +
                "directionId INT," +
                "FOREIGN KEY(directionId) REFERENCES Directions(id)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableEmployee() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Employee (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "firstName VARCHAR(255)," +
                "roleId INT," +
                "directionId INT," +
                "FOREIGN KEY(roleId) REFERENCES Roles(id)," +
                "FOREIGN KEY(directionId) REFERENCES Directions(id)" +
                ")";
        statement.executeUpdate(sql);
    }
    public void dropTable(String tableName) throws SQLException {
        // code
    }
    public void insertTableRoles(String roleName) throws SQLException {
        // code
    }
    public void insertTableDirections(String directionName) throws SQLException {
        // code
    }
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        // code
    }
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        // code
    }
//    public int getRoleId(String roleName) throws SQLException {
//        // code
//    }
//    public int getDirectionId(String directionName) throws SQLException {
//        // code
//    }
//    public int getProjectId(String projectName) throws SQLException {
//        // code
//    }
//    public int getEmployeeId(String firstName) throws SQLException {
//        // code
//    }
//    public List<String> getAllRoles() throws SQLException {
//        // code
//    }
//    public List<String> getAllDirestion() throws SQLException {
//        // code
//    }
//    public List<String> getAllProjects() throws SQLException {
//        // code
//    }
//    public List<String> getAllEmployee() throws SQLException {
//        // code
//    }
//    public List<String> getAllDevelopers() throws SQLException {
//        // code
//    }
//    public List<String> getAllJavaProjects() throws SQLException {
//        // code
//    }
//    public List<String> getAllJavaDevelopers() throws SQLException {
//        // code
//    }

    public static void main(String[] args) {
        MyUtils myUtils = new MyUtils();
        try {
            myUtils.createConnection();
            myUtils.createStatement();
            myUtils.createSchema("softserve");
            myUtils.useSchema();

            myUtils.createTableRoles();
            myUtils.createTableDirections();
            myUtils.createTableProjects();
            myUtils.createTableEmployee();

//            myUtils.dropTable("Roles");
//            myUtils.dropSchema();

            myUtils.closeStatement();
            myUtils.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



