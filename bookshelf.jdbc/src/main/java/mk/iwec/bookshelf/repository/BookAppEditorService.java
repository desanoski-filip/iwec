package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;

public interface BookAppEditorService extends BookAppService{
    boolean createBook(Book book);
    boolean deleteBook(String title);
    boolean updateBook(Book book);
}
