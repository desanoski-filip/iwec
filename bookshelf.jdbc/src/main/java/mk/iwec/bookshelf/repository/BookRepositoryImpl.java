package mk.iwec.bookshelf.repository;
import mk.iwec.bookshelf.db.DB;
import mk.iwec.bookshelf.domain.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BookRepositoryImpl implements BookRepository {

    private static final String SQL_SELECT = "SELECT * FROM books WHERE title = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM books";
    private static final String SQL_INSERT = "INSERT INTO books (title, author, publisher, year, edition, isbn, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM books WHERE title = ?";
    private static final String SQL_UPDATE = "UPDATE books SET author = ?, publisher = ?, year = ?, edition = ?, isbn = ?, category = ? WHERE title = ?";
    private static final String SQL_CHECK_EXISTENCE = "SELECT COUNT(*) FROM books WHERE title = ?";
    private static final String SQL_GET_BOOKS_BY_CATEGORY = "SELECT * FROM books WHERE category = ?";
    private static final String SQL_SEARCH_BOOKS = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";
    private static final String SQL_GET_BOOKS_BY_AUTHOR = "SELECT * FROM books WHERE author = ?";

    @Override
    public Book findByTitle(String title) {
        if (title == null) {
            return null;
        }

        Book result = null;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT);) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = extractBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        List<Book> books = new ArrayList<>();
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_GET_BOOKS_BY_CATEGORY);) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_SEARCH_BOOKS);) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_GET_BOOKS_BY_AUTHOR);) {
            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findAll() {
        List<Book> result = new LinkedList<>();
        try (DB db = new DB();
             Statement s = db.getConnection().createStatement();
             ResultSet rs = s.executeQuery(SQL_SELECT_ALL);) {

            while (rs.next()) {
                result.add(extractBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Book book) {
        if (book == null) {
            return 0;
        }

        // Check if a book with the same title already exists
        if (isBookExists(book.getTitle())) {
            System.out.println("Book with the same title already exists.");
            return 0;
        }

        int affected = 0;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_INSERT);) {
            ps.setString(1, book.getTitle());
            ps.setString(2, String.valueOf(book.getAuthors()));
            ps.setString(3, book.getPublisher());
            ps.setInt(4, book.getPublicationYear());
            ps.setInt(5, book.getEditionNumber());
            ps.setString(6, book.getIsbn());
            ps.setString(7, book.getCategories());

            affected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affected;
    }

    private boolean isBookExists(String title) {
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_CHECK_EXISTENCE)) {
            ps.setString(1, title);
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
    public int deleteByTitle(String title) {
        if (title == null) {
            return 0;
        }

        int affected = 0;
        try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_DELETE);) {
            ps.setString(1, title);
            affected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected;
    }

    @Override
    public int update(Book book) {
        if (book == null) {
            return 0;
        }

        try (Scanner scanner = new Scanner(System.in);
             DB db = new DB();
             PreparedStatement ps = db.getConnection().prepareStatement(SQL_UPDATE);) {

            System.out.println("Choose a field to update: ");
            System.out.println("1. Author");
            System.out.println("2. Publisher");
            System.out.println("3. Year");
            System.out.println("4. Edition");
            System.out.println("5. ISBN");
            System.out.println("6. Category");
            System.out.println("7. Cancel");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new author: ");
                    ps.setString(1, scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new publisher: ");
                    ps.setString(2, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new year: ");
                    ps.setInt(3, scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter new edition: ");
                    ps.setInt(4, scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Enter new ISBN: ");
                    ps.setString(5, scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter new category: ");
                    ps.setString(6, scanner.nextLine());
                    break;
                case 7:
                    System.out.println("Update canceled.");
                    return 0;
                default:
                    System.out.println("Invalid choice. No fields updated.");
                    return 0;
            }
            ps.setString(7, book.getTitle());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private Book extractBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString("title"));
        book.setAuthors(Collections.singletonList(resultSet.getString("author")));
        book.setPublisher(resultSet.getString("publisher"));
        book.setPublicationYear(resultSet.getInt("year"));
        book.setEditionNumber(resultSet.getInt("edition"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setCategories(Collections.singletonList(resultSet.getString("category")));
        return book;
    }
}
