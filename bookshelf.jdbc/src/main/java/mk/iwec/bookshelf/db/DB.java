package mk.iwec.bookshelf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB implements AutoCloseable {
    private Connection conn;

    public DB() {
        String url = "jdbc:postgresql://127.0.0.1:5432/bookstore"; // Change the database name
        String user = "postgres";
        String password = "desanoski";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);

            createBookTable();
            createUserTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createBookTable() {
        try (Statement statement = conn.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS books ("
                    + "id SERIAL PRIMARY KEY,"
                    + "title VARCHAR(100) NOT NULL,"
                    + "authors VARCHAR(255) NOT NULL,"
                    + "publisher VARCHAR(100) NOT NULL,"
                    + "publication_year INT NOT NULL,"
                    + "edition_number INT NOT NULL,"
                    + "isbn VARCHAR(20) NOT NULL,"
                    + "formats VARCHAR(255) NOT NULL,"
                    + "categories VARCHAR(255) NOT NULL,"
                    + "cover_image_file_name VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserTable() {
        try (Statement statement = conn.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "user_type VARCHAR(20) NOT NULL,"
                    + "contact_info VARCHAR(100) NOT NULL"
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
