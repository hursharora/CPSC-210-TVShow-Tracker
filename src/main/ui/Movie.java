package ui;

public class Movie implements Content {

    private String title;
    private String releaseYear;

    public Movie(String title) {
        this.title = title;
        System.out.println("Created new Movie called: " + title);
    }

    public Movie(String title, String releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
        System.out.println("Created new Movie called: " + title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
