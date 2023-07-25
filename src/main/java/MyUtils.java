import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyUtils {
    private Connection connection;
    private Statement statement;
    private String schemaName = "softserve";



    public Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        connection = DriverManager.getConnection("jdbc:h2:./test", "root", "root");
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
                "roleName VARCHAR(255) NOT NULL," +
                "UNIQUE (roleName)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableDirections() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Directions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "directionName VARCHAR(255) NOT NULL," +
                "UNIQUE (directionName)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableProjects() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Projects (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "projectName VARCHAR(255) NOT NULL," +
                "directionId INT NOT NULL," +
                "UNIQUE (projectName)," +
                "FOREIGN KEY(directionId) REFERENCES Directions(id)" +
                ")";
        statement.executeUpdate(sql);
    }

    public void createTableEmployee() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Employee (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "firstName VARCHAR(255) NOT NULL," +
                "roleId INT NOT NULL," +
                "projectId INT NOT NULL," +
                "UNIQUE (firstName)," +
                "FOREIGN KEY(roleId) REFERENCES Roles(id)," +
                "FOREIGN KEY(projectId) REFERENCES Projects(id)" +
                ")";
        statement.executeUpdate(sql);
    }
    public void dropTable(String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName + ";";
        statement.executeUpdate(sql);
    }
    public void insertTableRoles(String roleName) throws SQLException {
        String sql = "INSERT INTO  Roles (roleName) VALUES ('" + roleName + "');";
        statement.executeUpdate(sql);
    }
    public void insertTableDirections(String directionName) throws SQLException {
        String sql = "INSERT INTO  Directions (directionName) VALUES ('" + directionName + "');";
        statement.executeUpdate(sql);
    }
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        String sql = "INSERT INTO  Projects (projectName, directionId) VALUES ('" + projectName +
                "'," + getDirectionId(directionName) + ");";
        statement.executeUpdate(sql);
    }
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        String sql = "INSERT INTO  Employee (firstName, roleId, projectId) VALUES ('" + firstName +
                "'," + getRoleId(roleName) + "," + getProjectId(projectName) + ");";
        statement.executeUpdate(sql);
    }
    public int getRoleId(String roleName) throws SQLException {
        String sql = "SELECT id FROM Roles WHERE roleName LIKE '" + roleName + "';";
        ResultSet rs = statement.executeQuery(sql);
        int result = -1;
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }
    public int getDirectionId(String directionName) throws SQLException {
        String sql = "SELECT id FROM Directions WHERE directionName LIKE '" + directionName + "';";
        ResultSet rs = statement.executeQuery(sql);
        int result = -1;
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }
    public int getProjectId(String projectName) throws SQLException {
        String sql = "SELECT id FROM Projects WHERE projectName LIKE '" + projectName + "';";
        ResultSet rs = statement.executeQuery(sql);
        int result = -1;
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }
    public int getEmployeeId(String firstName) throws SQLException {
        String sql = "SELECT id FROM Employee WHERE firstName LIKE '" + firstName + "';";
        ResultSet rs = statement.executeQuery(sql);
        int result = -1;
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }


    public List<String> getAllRoles() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT roleName FROM Roles";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllDirestion() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT directionName FROM Directions";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllProjects() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT projectName FROM Projects";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllEmployee() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT firstName FROM Employee";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllDevelopers() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT e.firstName FROM Employee e LEFT JOIN Roles r " +
                    "ON e.roleId = r.id WHERE r.roleName LIKE 'Developer';";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllJavaProjects() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT p.projectName FROM Projects p LEFT JOIN Directions d " +
                "ON p.directionId = d.id WHERE d.directionName LIKE 'Java';";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    public List<String> getAllJavaDevelopers() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT e.firstName from Roles r JOIN Employee e ON r.id = e.roleId " +
                "JOIN Projects p ON e.projectId = p.id " +
                "JOIN Directions d ON p.directionId = d.id WHERE " +
                "d.directionName LIKE 'Java' AND r.roleName LIKE 'Developer';";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MyUtils myUtils = new MyUtils();
        try {
            myUtils.createConnection();
            myUtils.createStatement();
//            myUtils.createSchema("softserve");
            myUtils.useSchema();
//            myUtils.createTableRoles();
//            myUtils.createTableDirections();
//            myUtils.createTableProjects();
//            myUtils.createTableEmployee();

//            myUtils.insertTableRoles("Developer");
//            myUtils.insertTableRoles("Tester");
//            myUtils.insertTableDirections("Java");
//            myUtils.insertTableDirections("C++");
//            myUtils.insertTableProjects("Softserve", "Java");
//            myUtils.insertTableProjects("DOU", "Java");
//            myUtils.insertTableProjects("BeetRoot", "C++");
//            myUtils.insertTableProjects("IBM", "C++");
//            myUtils.insertTableProjects("DOU", "Java");
//            myUtils.insertTableProjects("DOU", "Java");
//            myUtils.insertTableEmployee("Petrov", "Developer", "Softserve");
//            myUtils.insertTableEmployee("Ivanov", "Tester", "Softserve");
//            myUtils.insertTableEmployee("Mikhaylov", "Tester", "DOU");
//            myUtils.insertTableEmployee("Mike", "Developer", "IBM");
//            myUtils.insertTableEmployee("Nick", "Tester", "IBM");
            myUtils.insertTableEmployee("Oleg", "Tester", "BettRoot");
            myUtils.insertTableEmployee("Olga", "Developer", "BettRoot");

//            System.out.println(myUtils.getAllRoles());
//            System.out.println(myUtils.getAllDirestion());
//            System.out.println(myUtils.getAllProjects());
//            System.out.println(myUtils.getAllEmployee());
//            System.out.println(myUtils.getAllDevelopers());
//            System.out.println(myUtils.getAllJavaProjects());
//            myUtils.dropTable("Roles");
//            myUtils.dropSchema();

            myUtils.closeStatement();
            myUtils.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



