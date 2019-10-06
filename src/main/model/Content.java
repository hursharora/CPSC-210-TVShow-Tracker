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
}
