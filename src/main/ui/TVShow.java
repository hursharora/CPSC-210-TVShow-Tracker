package ui;

//Represents a TVShow
public class TVShow implements Content {
    private int seasons;
    private int episodes;
    private String title;
    private String firstAired;
    private int rating;

    //MODIFIES: this
    //EFFECTS: creates TV show with a title
    public TVShow(String title) {
        this.title = title;
        System.out.println("Created new TV show titled: " + title);
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date
    public TVShow(int seasons, int episodes, String title, String firstAired) {
        this.seasons = seasons;
        this.episodes = episodes;
        this.title = title;
        this.firstAired = firstAired;
        System.out.println("Created new TV show titled: " + title);
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date, rating
    public TVShow(int seasons, int episodes, String title, String firstAired, int rating) {
        this.seasons = seasons;
        this.episodes = episodes;
        this.title = title;
        this.firstAired = firstAired;
        this.rating = rating;
        System.out.println("Created new TV show titled: " + title);
    }

    //EFFECTS: returns the title of TV Show
    @Override
    public String getTitle() {
        return title;
    }

    //MODIFIES: this
    //EFFECTS: sets title of TV Show
    @Override
    public void setTitle(String title) {
        System.out.println("Set title to" + title);
        this.title = title;
    }




}
