package network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.TVShow;
import model.exceptions.ShowNotFoundException;

import java.io.IOException;

public class ShowInfoGetter {

    private TVShowRequester tvShowRequester = new TVShowRequester();

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

    public int getNumEpisodes(long tvdbID) throws IOException, ShowNotFoundException {
        String epSummaryJson = tvShowRequester.showEpisodeSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(epSummaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        return objData.get("airedEpisodes").getAsInt();
    }

    public long getShowTVDbID(String title) throws IOException, ShowNotFoundException {
        String searchJson = tvShowRequester.searchShow(title);
        JsonObject obj = new Gson().fromJson(searchJson, JsonObject.class);
        JsonArray objData = obj.get("data").getAsJsonArray();
        JsonObject firstResult = objData.get(0).getAsJsonObject();
        return firstResult.get("id").getAsLong();
    }

    public String getFirstAirDate(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("firstAired").getAsString();
    }

    public int getRating(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("siteRating").getAsInt();
    }

    public String getShowName(long tvdbID) throws IOException, ShowNotFoundException {
        JsonObject objData = getSummaryJsonObject(tvdbID);
        return objData.get("seriesName").getAsString();
    }

    private JsonObject getSummaryJsonObject(long tvdbID) throws IOException, ShowNotFoundException {
        String summaryJson = tvShowRequester.showSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(summaryJson, JsonObject.class);
        return obj.get("data").getAsJsonObject();
    }



    public String getImageURL(long tvdbID) throws IOException, ShowNotFoundException {
        String imagePrefix = "https://artworks.thetvdb.com/banners/";
        String imageJson = tvShowRequester.showPosters(tvdbID);
        JsonObject obj = new Gson().fromJson(imageJson, JsonObject.class);
        JsonArray objArray = obj.get("data").getAsJsonArray();
        JsonObject firstPoster = objArray.get(0).getAsJsonObject();
        return imagePrefix + firstPoster.get("fileName").getAsString();
    }


}
