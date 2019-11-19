package model;

import model.exceptions.ShowNotFoundException;
import network.ShowInfoGetter;

import java.io.IOException;
import java.util.*;

//Represents a TVShow
public class TVShow extends Content {
    private int numEpisodes;
    private long tvdbID;
    private List<Season> listOfSeasons;
    private Map<Season, List<Content>> episodes = new HashMap<>();
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


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        try {
            ((TVShow)arg).tvdbID = ShowInfoGetter.getShowTVDbID(title);
            ((TVShow)arg).posterURL = ShowInfoGetter.getImageURL(tvdbID);
            ((TVShow)arg).release = ShowInfoGetter.getFirstAirDate(tvdbID);
            ((TVShow)arg).numEpisodes = ShowInfoGetter.getNumEpisodes(tvdbID);
            ((TVShow)arg).rate(ShowInfoGetter.getRating(tvdbID));
            System.out.println(tvdbID + " " + release + " " + numEpisodes + " " + rating + " " + posterURL);

        } catch (IOException | ShowNotFoundException e) {
            e.printStackTrace();
        }
    }
}
