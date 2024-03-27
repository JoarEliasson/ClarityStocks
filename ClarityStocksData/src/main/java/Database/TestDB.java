package Database;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
public class TestDB {
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(TestDB.class.getName());
    }

    public static void main(String[] args) {
        try {
            log.info("Loading application properties");
            Properties properties = loadApplicationProperties();

            log.info("Connecting to the database");
            Connection connection = connectToDatabase(properties);

            // Your existing logic for using the connection
            Todo todo = new Todo(1L, "configuration", "congratulations, you have set up JDBC correctly!", true);
            insertData(todo, connection);

            todo = readData(connection);

            log.info("Closing database connection");
            disconnectFromDatabase(connection);
        } catch (Exception e) {
            log.severe("Failed in main: " + e.getMessage());
        }
    }

    private static Properties loadApplicationProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(TestDB.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }

    private static Connection connectToDatabase(Properties properties) throws SQLException {
        Connection conn = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + conn.getCatalog());
        return conn;
    }

    private static void disconnectFromDatabase(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                log.info("Database conn closed successfully.");
            } catch (SQLException e) {
                log.severe("Failed to close database conn: " + e.getMessage());
            }
        }
    }

    private static void insertData(Todo todo, Connection conn) throws SQLException {
        log.info("Insert data");
        PreparedStatement insertStatement = conn
                .prepareStatement("INSERT INTO todo (id, description, details, done) VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, todo.getId());
        insertStatement.setString(2, todo.getDescription());
        insertStatement.setString(3, todo.getDetails());
        insertStatement.setBoolean(4, todo.isDone());
        insertStatement.executeUpdate();
    }

    private static Todo readData(Connection conn) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = conn.prepareStatement("SELECT * FROM todo;");
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("There is no data in the database!");
            return null;
        }
        Todo todo = new Todo();
        todo.setId(resultSet.getLong("id"));
        todo.setDescription(resultSet.getString("description"));
        todo.setDetails(resultSet.getString("details"));
        todo.setDone(resultSet.getBoolean("done"));
        log.info("Data read from the database: " + todo.toString());
        return todo;
    }
}
