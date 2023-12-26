package mk.iwec.bookshelf.repository;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.User;

import java.util.List;

public class BookAppEditorServiceImpl implements BookAppEditorService {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BookAppEditorServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean loginUser(String username, String password) {
        // Implementation for editor login
        return false;
    }

    @Override
    public boolean logoutUser(String username) {
        // Implementation for editor logout
        return false;
    }

    @Override
    public boolean createUser(User user) {
        // Implementation for creating an editor user
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        // Implementation for deleting an editor user
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        // Implementation for updating an editor user
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
    public boolean createBook(Book book) {
        // Implementation for creating a book as an editor
        return false;
    }

    @Override
    public boolean deleteBook(String title) {
        // Implementation for deleting a book as an editor
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        // Implementation for updating a book as an editor
        return false;
    }
}

