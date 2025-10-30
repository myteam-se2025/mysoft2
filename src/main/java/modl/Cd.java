package modl;

/**
 * @auther khadeja and masa
 * @param Cd class to represent a CD in the system
 * @param cd_id int unique identifier for the CD
 * @param title String title of the CD
 * @param artist String artist name of the CD
 * @param genre String music genre of the CD
 * @param available_copies int number of available copies of the CD
 */

public class Cd {

    int cd_id = 0;
    String title = "null";
    String artist = "null";
    String genre = "null";
    int available_copies = 0;

    /* public Cd(int cd_id, String title, String artist, String genre, int available_copies) {
        this.cd_id = cd_id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.available_copies = available_copies;
    } */

    public Cd(String title, String artist, String genre, int available_copies) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.available_copies = available_copies;
    }

    public int getCd_id() {
        return cd_id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setCd_id(int cd_id) {
        this.cd_id = cd_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }
}
