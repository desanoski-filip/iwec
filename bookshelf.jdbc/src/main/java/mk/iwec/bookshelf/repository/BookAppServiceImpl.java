package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.User;

import java.util.List;

public class BookAppServiceImpl implements BookAppService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookAppServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    @Override
    public boolean logoutUser(String username) {
        System.out.println("Logout successful. Goodbye, " + username + "!");
        return true;
    }

    @Override
    public boolean createUser(User user) {
        int result = userRepository.insert(user);
        if (result > 0) {
            System.out.println("User created successfully: " + user.getUsername());
            return true;
        } else {
            System.out.println("Failed to create user. Please check the provided information.");
            return false;
        }
    }

    @Override
    public boolean deleteUser(String username) {
        int result = userRepository.deleteByUsername(username);
        if (result > 0) {
            System.out.println("User deleted successfully: " + username);
            return true;
        } else {
            System.out.println("Failed to delete user. Please check the provided username.");
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        int result = userRepository.update(user);
        if (result > 0) {
            System.out.println("User updated successfully: " + user.getUsername());
            return true;
        } else {
            System.out.println("Failed to update user. Please check the provided information.");
            return false;
        }
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.getBooksByCategory(category);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }
}
