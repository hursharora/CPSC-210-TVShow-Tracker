package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents a TVShow
public class TVShow extends Content {
    private List<Season> listOfSeasons;
    private Map<Season, List<Content>> episodes = new HashMap<>();


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

    @Override
    public void toggleWatched() {
        super.toggleWatched();
        for (Season listOfSeason : listOfSeasons) {
            listOfSeason.toggleWatched();
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








}
