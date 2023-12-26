package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.Book;

import java.util.List;

public interface BookAppRegularService extends BookAppService {
    List<Book> browseBooks();

    Book viewBookDetails(String title);
}
