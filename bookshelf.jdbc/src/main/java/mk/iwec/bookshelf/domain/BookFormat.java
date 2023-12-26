package mk.iwec.bookshelf.domain;
import java.util.Objects;

public class BookFormat {
    private Book book;  // Reference to the associated book
    private String formatType;
    private String fileName;

    // Constructor
    public BookFormat(Book book, String formatType, String fileName) {
        this.book = book;
        this.formatType = formatType;
        this.fileName = fileName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Getters, setters, and other methods.

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookFormat otherFormat = (BookFormat) obj;
        return Objects.equals(formatType, otherFormat.formatType)
                && Objects.equals(fileName, otherFormat.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatType, fileName);
    }

    @Override
    public String toString() {
        return "BookFormat{" +
                "Book =" + book +
                ", FormatType ='" + formatType + '\'' +
                ", File Name ='" + fileName + '\'' +
                '}';
    }
}


