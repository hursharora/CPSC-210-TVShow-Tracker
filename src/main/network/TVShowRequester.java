package network;

import model.exceptions.ShowNotFoundException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TVShowRequester extends HttpRequester {

    private String authToken = TVDbAuthorization.parseForAuthToken();

    public TVShowRequester() throws IOException, ShowNotFoundException {
    }

    //EFFECTS: returns json response from TVDB of searching a tv show
    public String searchShow(String query) throws IOException, ShowNotFoundException {
        String urlReadyQuery = query.replaceAll("\\s+", "+");
        return super.makeHttpRequest("https://api.thetvdb.com/search/series?name=" + urlReadyQuery);

    }

    public String showEpisodeSummary(long id) throws IOException, ShowNotFoundException {
        String urlReadyQuery = "https://api.thetvdb.com/series/<id>/episodes/summary".replaceAll("<id>", String.valueOf(id));
        return super.makeHttpRequest(urlReadyQuery);
    }

    public String showSummary(long id) throws IOException, ShowNotFoundException {
        String urlReadyQuery = "https://api.thetvdb.com/series/<id>".replaceAll("<id>", String.valueOf(id));
        return super.makeHttpRequest(urlReadyQuery);
    }

    @Override
    protected HttpURLConnection getURlConnection(URL url) throws IOException, ShowNotFoundException {
        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestProperty("Authorization", "Bearer " + authToken);
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 404) {
            throw new ShowNotFoundException();
        }
        return urlConnection;
    }



}
