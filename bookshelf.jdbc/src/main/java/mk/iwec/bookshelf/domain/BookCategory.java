package mk.iwec.bookshelf.domain;
import java.util.List;
import java.util.Objects;

public class BookCategory {
    private List<Book> books;
    private String categoryName;

    // Constructor
    public BookCategory(List<Book> books, String categoryName) {
        this.books = books;
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookCategory otherCategory = (BookCategory) obj;
        return Objects.equals(categoryName, otherCategory.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "Books =" + books +
                ", Category Name='" + categoryName + '\'' +
                '}';
    }
}

