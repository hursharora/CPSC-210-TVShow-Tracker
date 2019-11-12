package network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.exceptions.ShowNotFoundException;

import java.io.IOException;

public class ShowInfoGetter {

    public static int getNumEpisodes(long tvdbID) throws IOException, ShowNotFoundException {
        TVShowRequester tvShowRequester = new TVShowRequester();
        String epSummaryJson = tvShowRequester.showEpisodeSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(epSummaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        return objData.get("airedEpisodes").getAsInt();
    }

    public static int getNumEpisodes(String title) throws IOException, ShowNotFoundException {
        long showID = getShowTVDbID(title);
        return getNumEpisodes(showID);
    }

    public static long getShowTVDbID(String title) throws IOException, ShowNotFoundException {
        TVShowRequester tvShowRequester = new TVShowRequester();
        String searchJson = tvShowRequester.searchShow(title);
        JsonObject obj = new Gson().fromJson(searchJson, JsonObject.class);
        JsonArray objData = obj.get("data").getAsJsonArray();
        JsonObject firstResult = objData.get(0).getAsJsonObject();
        return firstResult.get("id").getAsLong();
    }

    public static String getFirstAirDate(long tvdbID) throws IOException, ShowNotFoundException {
        TVShowRequester tvShowRequester = new TVShowRequester();
        String summaryJson = tvShowRequester.showSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(summaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        return objData.get("firstAired").getAsString();
    }

    public static int getRating(long tvdbID) throws IOException, ShowNotFoundException {
        TVShowRequester tvShowRequester = new TVShowRequester();
        String summaryJson = tvShowRequester.showSummary(tvdbID);
        JsonObject obj = new Gson().fromJson(summaryJson, JsonObject.class);
        JsonObject objData = obj.get("data").getAsJsonObject();
        return objData.get("siteRating").getAsInt();
    }

}
