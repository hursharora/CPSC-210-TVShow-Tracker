package model;


import model.exceptions.ShowNotFoundException;
import network.ShowInfoGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents a TVShow
public class TVShow extends Content {


    private int numEpisodes;
    private long tvdbID;
    private List<Season> listOfSeasons;
    private Map<Season, List<Content>> episodes;
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
        this.title = title;
        this.releaseDate = firstAired;
        this.rating = rating;
        this.tvdbID = id;
        this.numEpisodes = numEpisodes;
        this.posterURL = posterURL;
        try {
            listOfSeasons = initializeSeasons();
        } catch (IOException | ShowNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    //MODIFIES: this
    //EFFECTS: marks this show and all of its seasons as watched
    public void toggleWatched() {
        super.toggleWatched();
        for (Season s : listOfSeasons) {
            s.toggleWatched();
        }

    }

    public List<Content> getEpisodesFromSeason(Season s) {
        return episodes.get(s);
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

    //MODIFES: this
    //EFFECTS: fills in the episode hashmap with seasons and episodes
    public void loadEpisodes() throws IOException, ShowNotFoundException {
        episodes = new HashMap<>();
        for (Season s : listOfSeasons) {
            episodes.put(s, ShowInfoGetter.getEpisodes(tvdbID, s.getSeasonNumber()));
        }
    }


    private ArrayList<Season> initializeSeasons() throws IOException, ShowNotFoundException {
        return ShowInfoGetter.getSeasons(tvdbID);
    }

    public List<Season> getListOfSeasons() {
        return listOfSeasons;
    }

    public Map<Season, List<Content>> getEpisodes() {
        return episodes;
    }



}