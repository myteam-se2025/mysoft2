package modl;

/**
 * Represents a CD in the library management system.
 * Contains information such as title, artist, genre, and available copies.
 * Provides constructors, getters, and setters for all fields.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 * @since 2025
 */

public class Cd {

	int cd_id = 0;
	String title = "null";
	String artist = "null";
	String genre = "null";
	int available_copies = 0;

	 /**
     * Constructs a CD with all fields specified.
     *
     * @param title the title of the CD
     * @param artist the artist name
     * @param genre the music genre
     * @param available_copies number of available copies
     */
    public Cd(String title, String artist, String genre, int available_copies) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.available_copies = available_copies;
    }

    /** @return the artist name of the CD */
    public String getArtist() {
        return artist;
    }

    /** @return the number of available copies */
    public int getAvailable_copies() {
        return available_copies;
    }

    /** @return the CD ID */
    public int getCd_id() {
        return cd_id;
    }

    /** @return the genre of the CD */
    public String getGenre() {
        return genre;
    }

    /** @return the title of the CD */
    public String getTitle() {
        return title;
    }

    /** @param artist sets the artist name */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /** @param available_copies sets the number of available copies */
    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }

    /** @param cd_id sets the CD ID */
    public void setCd_id(int cd_id) {
        this.cd_id = cd_id;
    }

    /** @param genre sets the genre of the CD */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /** @param title sets the title of the CD */
    public void setTitle(String title) {
        this.title = title;
    }
}
