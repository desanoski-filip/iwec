package mk.iwec.jdbc.mssql.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DB implements AutoCloseable {
    private Connection conn;

    public DB() {
        String url = "jdbc:postgresql://127.0.0.1:5432/inventory"; 
        String user = "postgres";
        String password = "desanoski";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            createInventoryTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createInventoryTable() {
        try (Statement statement = conn.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS inventory ("
                    + "id SERIAL PRIMARY KEY,"
                    + "product_id INT NOT NULL,"
                    + "category VARCHAR(10) NOT NULL,"
                    + "product_name VARCHAR(50) NOT NULL,"
                    + "price FLOAT NOT NULL,"
                    + "quantity INT NOT NULL"
                    + ")";
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}