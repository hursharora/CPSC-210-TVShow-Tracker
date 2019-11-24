package network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Content;
import model.Episode;
import model.Season;
import model.TVShow;
import model.exceptions.ShowNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

//Parses JSON and returns desired data
public class ShowInfoGetter {

    private static TVShowRequester tvShowRequester;

    static {
        try {
            tvShowRequester = new TVShowRequester();
        } catch (IOException | ShowNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TVShow getSearchedShow() {
        return searchedShow;
    }

    private TVShow searchedShow;


    public ShowInfoGetter(String searchQuery) throws IOException, ShowNotFoundException {
        long tvdbID = getShowTVDbID(searchQuery);
        String title = getShowName(tvdbID);
        String firstAired = getFirstAirDate(tvdbID);
        int rating = getRating(tvdbID);
        int numEpisodes = getNumEpisodes(tvdbID);
        String posterURL = getImageURL(tvdbID);
        searchedShow = new TVShow(title, firstAired, rating, tvdbID, numEpisodes, posterURL);
    }

    private int getNumEpisodes(long tvdbID) throws IOException, ShowNotFoundException {
        String epSummaryJson = tvShowRequester.showEpisodeSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(epSummaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        return objData.get("airedEpisodes").getAsInt();
    }

    private long getShowTVDbID(String title) throws IOException, ShowNotFoundException {
        String searchJson = tvShowRequester.searchShow(title);
        JsonObject obj = new Gson().fromJson(searchJson, JsonObject.class);
        JsonArray objData = obj.get("data").getAsJsonArray();
        JsonObject firstResult = objData.get(0).getAsJsonObject();
        return firstResult.get("id").getAsLong();
    }

    private String getFirstAirDate(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("firstAired").getAsString();
    }

    private int getRating(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("siteRating").getAsInt();
    }

    private String getShowName(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("seriesName").getAsString();
    }

    private JsonObject getSummaryJsonObject(long tvdbID) throws IOException, ShowNotFoundException {
        String summaryJson = tvShowRequester.showSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(summaryJson, JsonObject.class);
        return obj.get("data").getAsJsonObject();
    }



    private String getImageURL(long tvdbID) throws IOException, ShowNotFoundException {
        String imagePrefix = "https://artworks.thetvdb.com/banners/";
        String imageJson = tvShowRequester.showPosters(tvdbID);
        JsonObject obj = new Gson().fromJson(imageJson, JsonObject.class);
        JsonArray objArray = obj.get("data").getAsJsonArray();
        JsonObject firstPoster = objArray.get(0).getAsJsonObject();
        return imagePrefix + firstPoster.get("fileName").getAsString();
    }

    //EFFECTS: returns list of seasons given a tvdb id by making api calls and parsing json
    public static ArrayList<Season> getSeasons(long tvdbID) throws IOException, ShowNotFoundException {
        ArrayList<Season> returnedList = new ArrayList<>();
        String epSummaryJson = tvShowRequester.showEpisodeSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(epSummaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        JsonArray seasonArray =  objData.get("airedSeasons").getAsJsonArray();
        for (int i = 0; i < seasonArray.size(); i++) {
            returnedList.add(new Season(seasonArray.get(i).getAsInt()));
        }
        return returnedList;
    }

    //EFFECTS: returns a list of episodes given a tvdb id and and seasons number by making api calls and parsing json
    public static ArrayList<Content> getEpisodes(long tvdbID, int seaNum) throws IOException, ShowNotFoundException {
        ArrayList<Content> returnedList = new ArrayList<>();
        String seasonEps = tvShowRequester.showEpisodesFromSeason(tvdbID, seaNum);
        JsonObject obj = new Gson().fromJson(seasonEps, JsonObject.class);
        JsonArray arrData = obj.get("data").getAsJsonArray();
        for (int i = 0; i < arrData.size(); i++) {
            JsonObject epsObj = arrData.get(i).getAsJsonObject();
            returnedList.add(new Episode(epsObj.get("episodeName").getAsString()));
        }
        return returnedList;
    }




}
