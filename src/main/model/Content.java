package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Content {

    protected String title;
    protected String release;
    protected boolean watched;
    protected int rating = -1;

    public int getRating() {
        return rating;
    }

    protected List<Genre> genreList = new ArrayList<>();

    public boolean isWatched() {
        return watched;
    }

    //EFFECTS: returns the title of the content
    public String getTitle() {
        return this.title;
    }

    //MODIFIES: this
    //EFFECTS: sets title of content
    public void setTitle(String title) {
        this.title = title;
    }

    public void toggleWatched() {
        watched = !watched;
    }

    public void rate(int rating) {
        if (rating <= 10 && rating >= 0) {
            this.rating = rating;
        } else {
            System.out.println("Invalid Rating");
        }

    }

    public void addGenre(Genre genre) {
        if (!genreList.contains(genre)) {
            genreList.add(genre);
            genre.addContent(this);
        }
    }

    public void removeGenre(Genre genre) {
        if (genreList.contains(genre)) {
            genreList.remove(genre);
            genre.removeContent(this);
        }
    }

    public List<Genre> getGenres() {
        return genreList;
    }

}
