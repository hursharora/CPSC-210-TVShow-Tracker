package model;

public class Movie extends Content {

    public Movie(String title) {
        this.title = title;
        System.out.println("Created new Movie called: " + title);
    }

    public Movie(String title, String releaseYear, int rating) {
        this.rating = rating;
        this.title = title;
        this.release = releaseYear;
        System.out.println("Created new Movie called: " + title);
    }

}
