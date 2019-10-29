package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents a TVShow
public class TVShow extends Content {
    private ListOfContent listOfSeasons;
    private Map<Season, List<Content>> episodes = new HashMap<>();


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
