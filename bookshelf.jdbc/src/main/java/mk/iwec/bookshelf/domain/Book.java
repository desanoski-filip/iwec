package mk.iwec.bookshelf.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Book {

    private String title;
    private List<String> authors;
    private String publisher;
    private int publicationYear;
    private int editionNumber;
    private String isbn;
    private Map<String, String> formats;  // e.g., {"PDF": "filename.pdf", "HTML": "filename.html"}
    private List<String> categories;      // e.g., {"Java", "RDBMS"}
    private String coverImageFileName;


    public Book() {

    }

    public Book(String title, List<String> authors, String publisher, int publicationYear, int editionNumber,
                String isbn, Map<String, String> formats, List<String> categories, String coverImageFileName) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.editionNumber = editionNumber;
        this.isbn = isbn;
        this.formats = formats;
        this.categories = categories;
        this.coverImageFileName = coverImageFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public String getCategories() {
        return String.valueOf(categories);
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCoverImageFileName() {
        return coverImageFileName;
    }

    public void setCoverImageFileName(String coverImageFileName) {
        this.coverImageFileName = coverImageFileName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book otherBook = (Book) obj;
        return Objects.equals(isbn, otherBook.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "Title ='" + title + '\'' +
                ", Author/s =" + authors +
                ", Publisher ='" + publisher + '\'' +
                ", PublicationYear =" + publicationYear +
                ", EditionNumber =" + editionNumber +
                ", ISBN ='" + isbn + '\'' +
                ", Formats =" + formats +
                ", Categories =" + categories +
                ", Cover Image File Name ='" + coverImageFileName + '\'' +
                '}';
    }


}

