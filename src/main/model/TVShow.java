package model;

//Represents a TVShow
public class TVShow extends Content {
    private ListOfContent listOfSeasons;

    //MODIFIES: this
    //EFFECTS: creates TV show with a title
    public TVShow(String title) {
        this.title = title;
        System.out.println("Created new TV show titled: " + title);
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date
    public TVShow(ListOfContent seasons, String title, String firstAired) {
        this.listOfSeasons = seasons;
        this.title = title;
        this.release = firstAired;
        System.out.println("Created new TV show titled: " + title);
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date, rating
    public TVShow(ListOfContent seasons, String title, String firstAired, int rating) {
        this.listOfSeasons = seasons;
        this.title = title;
        this.release = firstAired;
        this.rating = rating;
        System.out.println("Created new TV show titled: " + title);
    }

    @Override
    public void toggleWatched() {
        super.toggleWatched();
        for (int i = 0; i < listOfSeasons.size(); i++) {
            listOfSeasons.getContent(i).toggleWatched();
        }

    }








}
