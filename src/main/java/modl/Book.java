
package modl;

/**
 * @auther khadeja and masa
 * @param Book class to represent a Book in the system
 * @param book_id int unique identifier for the Book
 * @param title String title of the Book
 * @param author String author name of the Book
 * @param isbn String ISBN number of the Book
 * @param category String category or genre of the Book
 * @param available_copies int number of available copies of the Book
 */

public class Book {

    int book_id = 0;
    String title = "null";
    String author = "null";
    String isbn = "null";
    String category = "null";
    int available_copies = 0;

    public Book(String title, String author, String isbn, String category, int available_copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available_copies = available_copies;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }
}