package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.db.DB;
import mk.iwec.bookshelf.domain.User;
import mk.iwec.bookshelf.domain.UserType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_INSERT = "INSERT INTO users (username, password, user_type, contact_info) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_BY_USERNAME = "DELETE FROM users WHERE username = ?";
    private static final String SQL_UPDATE = "UPDATE users SET password = ?, user_type = ?, contact_info = ? WHERE username = ?";
    private static final String SQL_CHECK_EXISTENCE = "SELECT COUNT(*) FROM users WHERE username = ?";

    @Override
    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }

        User result = null;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT_BY_USERNAME)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new LinkedList<>();
        try (DB db = new DB(); Statement s = db.getConnection().createStatement(); ResultSet rs = s.executeQuery(SQL_SELECT_ALL)) {

            while (rs.next()) {
                result.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(User user) {
        if (user == null) {
            return 0;
        }

        // Check if a user with the same username already exists
        if (isUserExists(user.getUsername())) {
            System.out.println("User with the same username already exists.");
            return 0;
        }

        int affected = 0;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType().toString());
            ps.setString(4, user.getContactInfo());

            affected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affected;
    }

    private boolean isUserExists(String username) {
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_CHECK_EXISTENCE)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteByUsername(String username) {
        if (username == null) {
            return 0;
        }

        int affected = 0;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_DELETE_BY_USERNAME)) {
            ps.setString(1, username);
            affected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected;
    }

    @Override
    public int update(User user) {
        if (user == null) {
            return 0;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            int affected = 0;

            try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_UPDATE)) {
                System.out.println("Choose a field to update: ");
                System.out.println("1. Password");
                System.out.println("2. User Type");
                System.out.println("3. Contact Info");
                System.out.println("4. Cancel");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new password: ");
                        ps.setString(1, scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new user type: ");
                        ps.setString(2, scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new contact info: ");
                        ps.setString(3, scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Update canceled.");
                        return 0;
                    default:
                        System.out.println("Invalid choice. No fields updated.");
                        return 0;
                }
                ps.setString(4, user.getUsername());

                affected = ps.executeUpdate();
                System.out.println("User updated successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return affected;
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setUserType(UserType.valueOf(resultSet.getString("user_type")));
        user.setContactInfo(resultSet.getString("contact_info"));
        return user;
    }
}
