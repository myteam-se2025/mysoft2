
package modl;

/**
 * Represents a Book in the library management system.
 * Contains information such as title, author, ISBN, category, and available copies.
 * Provides constructors, getters, and setters for all fields.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 * @since 2025
 */

public class Book {

	int book_id = 0;
	String title = "null";
	String author = "null";
	String isbn = "null";
	String category = "null";
	int available_copies = 0;

	 /**
     * Constructs a Book with all fields specified.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param isbn the ISBN number
     * @param category the category or genre
     * @param available_copies the number of available copies
     */
	public Book(String title, String author, String isbn, String category, int available_copies) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.category = category;
		this.available_copies = available_copies;
	}

	public Book() {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available_copies = available_copies;
    }
	/** @return the author of the book */
    public String getAuthor() {
        return author;
    }

    /** @return the number of available copies */
    public int getAvailable_copies() {
        return available_copies;
    }

    /** @return the book ID */
    public int getBook_id() {
        return book_id;
    }

    /** @return the category of the book */
    public String getCategory() {
        return category;
    }

    /** @return the ISBN number of the book */
    public String getIsbn() {
        return isbn;
    }

    /** @return the title of the book */
    public String getTitle() {
        return title;
    }

    /** @param author sets the author of the book */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** @param available_copies sets the number of available copies */
    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }

    /** @param book_id sets the book ID */
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    /** @param category sets the category of the book */
    public void setCategory(String category) {
        this.category = category;
    }

    /** @param isbn sets the ISBN number of the book */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /** @param title sets the title of the book */
    public void setTitle(String title) {
        this.title = title;
    }
}