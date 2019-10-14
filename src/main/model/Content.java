package model;

public abstract class Content {

    protected String title;
    protected String release;
    protected boolean watched;
    protected int rating;

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
}
