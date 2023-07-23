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
        connection = DriverManager.getConnection("jdbc:h2:mem:test", "root", "");
        return connection;
    }
    public void closeConnection() throws SQLException {
        // code
    }
    public Statement createStatement() throws SQLException {
        // code
    }
    public void closeStatement() throws SQLException {
        // code
    }
    public void createSchema(String schemaName) throws SQLException {
        // code
    }
    public void dropSchema() throws SQLException {
        // code
    }
    public void useSchema() throws SQLException {
        // code
    }
    public void createTableRoles() throws SQLException {
        // code
    }
    public void createTableDirections() throws SQLException {
        // code
    }
    public void createTableProjects() throws SQLException {
        // code
    }
    public void createTableEmployee() throws SQLException {
        // code
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
    public int getRoleId(String roleName) throws SQLException {
        // code
    }
    public int getDirectionId(String directionName) throws SQLException {
        // code
    }
    public int getProjectId(String projectName) throws SQLException {
        // code
    }
    public int getEmployeeId(String firstName) throws SQLException {
        // code
    }
    public List<String> getAllRoles() throws SQLException {
        // code
    }
    public List<String> getAllDirestion() throws SQLException {
        // code
    }
    public List<String> getAllProjects() throws SQLException {
        // code
    }
    public List<String> getAllEmployee() throws SQLException {
        // code
    }
    public List<String> getAllDevelopers() throws SQLException {
        // code
    }
    public List<String> getAllJavaProjects() throws SQLException {
        // code
    }
    public List<String> getAllJavaDevelopers() throws SQLException {
        // code
    }

}


