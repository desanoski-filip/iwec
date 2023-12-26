package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.User;

import java.util.List;

public interface BookAppService {
    boolean loginUser(String username, String password);
    boolean logoutUser(String username);
    boolean createUser(User user);
    boolean deleteUser(String username);
    boolean updateUser(User user);
    List<Book> searchBooks(String keyword);
    List<Book> getBooksByCategory(String category);
    List<Book> getBooksByAuthor(String author);
}
