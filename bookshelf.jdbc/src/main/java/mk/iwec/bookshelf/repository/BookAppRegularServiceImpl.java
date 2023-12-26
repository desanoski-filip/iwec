package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.User;

import java.util.List;

public class BookAppRegularServiceImpl implements BookAppRegularService {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BookAppRegularServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean loginUser(String username, String password) {
        // Implementation for regular user login
        return false;
    }

    @Override
    public boolean logoutUser(String username) {
        // Implementation for regular user logout
        return false;
    }

    @Override
    public boolean createUser(User user) {
        // Implementation for creating a regular user
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        // Implementation for deleting a regular user
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        // Implementation for updating a regular user
        return false;
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

    @Override
    public List<Book> browseBooks() {
        // Implementation for browsing books as a regular user
        return null;
    }

    @Override
    public Book viewBookDetails(String title) {
        // Implementation for viewing book details as a regular user
        return null;
    }
}
