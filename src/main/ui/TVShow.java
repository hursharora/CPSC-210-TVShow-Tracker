package ui;

public class TVShow {
    private int seasons;
    private int episodes;
    private String title;
    private String firstAired;
    private int rating;

    public TVShow(String title) {
        this.title = title;
        System.out.println("Created new TV show titled: " + title);
    }

    public TVShow(int seasons, int episodes, String title, String firstAired) {
        this.seasons = seasons;
        this.episodes = episodes;
        this.title = title;
        this.firstAired = firstAired;
        System.out.println("Created new TV show titled: " + title);
    }

    public TVShow(int seasons, int episodes, String title, String firstAired, int rating) {
        this.seasons = seasons;
        this.episodes = episodes;
        this.title = title;
        this.firstAired = firstAired;
        this.rating = rating;
        System.out.println("Created new TV show titled: " + title);
    }

    public String getTitle() {
        System.out.println(title);
        return title;
    }

    public void setTitle(String title) {
        System.out.println("Set title to" + title);
        this.title = title;
    }


}
