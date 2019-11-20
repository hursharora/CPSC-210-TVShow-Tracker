package model;


import java.util.*;

//Represents a TVShow
public class TVShow extends Content {


    private int numEpisodes;
    private long tvdbID;
    private List<Season> listOfSeasons;
    private Map<Season, List<Content>> episodes = new HashMap<>();



    private String releaseDate;



    private String posterURL;


    //MODIFIES: this
    //EFFECTS: creates TV show with a title
    public TVShow(String title) {
        this.title = title;
        System.out.println("Created new TV show titled: " + title);
        listOfSeasons = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date
    public TVShow(String title, String firstAired) {
        listOfSeasons = new ArrayList<>();
        this.title = title;
        this.release = firstAired;
        System.out.println("Created new TV show titled: " + title);
    }

    //MODIFIES: this
    //EFFECTS: creates TV show with seasons, episodes, title, first air date, rating
    public TVShow(String title, String firstAired, int rating) {
        listOfSeasons = new ArrayList<>();
        this.title = title;
        this.release = firstAired;
        this.rating = rating;
        System.out.println("Created new TV show titled: " + title);
    }

    public TVShow(String title, String firstAired, int rating, long id, int numEpisodes, String posterURL) {
        listOfSeasons = new ArrayList<>();
        this.title = title;
        this.releaseDate = firstAired;
        this.rating = rating;
        this.tvdbID = id;
        this.numEpisodes = numEpisodes;
        this.posterURL = posterURL;
    }

    @Override
    public void toggleWatched() {
        super.toggleWatched();
        for (Season s : listOfSeasons) {
            s.toggleWatched();
        }

    }

    public List<Content> getEpisodes(Season s) {
        return episodes.get(s);
    }

    public void addSeason(Season season) {
        episodes.put(season, new ArrayList<>());
    }

    public void addEpisode(Season season, Episode episode) {
        List<Content> seasonList = episodes.get(season);
        seasonList.add(episode);
    }

    public String getPosterURL() {
        return posterURL;
    }

    public int getNumEpisodes() {
        return numEpisodes;
    }

    public long getTvdbID() {
        return tvdbID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }



}