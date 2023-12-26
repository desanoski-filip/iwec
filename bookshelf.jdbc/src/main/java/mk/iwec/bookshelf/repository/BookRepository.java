package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;

import java.util.List;

public interface BookRepository {
    Book findByTitle(String title);

    List<Book> searchBooks(String keyword);
    List<Book> getBooksByCategory(String category);

    List<Book> getBooksByAuthor(String author);
    List<Book> findAll();
    int insert(Book book);
    int deleteByTitle(String title);
    int update(Book book);
}
