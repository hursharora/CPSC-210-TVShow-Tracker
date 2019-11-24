package network;

import model.exceptions.ShowNotFoundException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//Contains methods that take in input as tvdb id or title and form a url which is passed into a tvshow requester
public class TVShowRequester extends HttpRequester {

    private TVDbAuthorization tvDbAuthorization = new TVDbAuthorization();

    TVShowRequester() throws IOException, ShowNotFoundException {
    }

    //EFFECTS: returns json response from TVDB of searching a tv show
    String searchShow(String query) throws IOException, ShowNotFoundException {
        String urlReadyQuery = query.replaceAll("\\s+", "%20");
        return super.makeHttpRequest("https://api.thetvdb.com/search/series?name=" + urlReadyQuery);

    }

    String showEpisodeSummary(long id) throws IOException, ShowNotFoundException {
        String urlReadyQuery = "https://api.thetvdb.com/series/<id>/episodes/summary".replaceAll("<id>", String.valueOf(id));
        return super.makeHttpRequest(urlReadyQuery);
    }

    String showSummary(long id) throws IOException, ShowNotFoundException {
        String urlReadyQuery = "https://api.thetvdb.com/series/<id>".replaceAll("<id>", String.valueOf(id));
        return super.makeHttpRequest(urlReadyQuery);
    }

    String showPosters(long id) throws IOException, ShowNotFoundException {
        String urlReadyQuery = "https://api.thetvdb.com/series/<id>/images/query?keyType=poster".replaceAll("<id>", String.valueOf(id));
        return super.makeHttpRequest(urlReadyQuery);
    }

    String showEpisodesFromSeason(long id, int seasonNum) throws IOException, ShowNotFoundException {
        String requestUrl = "https://api.thetvdb.com/series/<id>/episodes/query?airedSeason=<sea>";
        String requestUrlWithID = requestUrl.replaceAll("<id>", String.valueOf(id));
        String urlReadyQuery = requestUrlWithID.replaceAll("<sea>", String.valueOf(seasonNum));
        //System.out.println(urlReadyQuery);
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
        urlConnection.setRequestProperty("Authorization", "Bearer " + tvDbAuthorization.getAuthToken());
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 404) {
            throw new ShowNotFoundException();
        }
        return urlConnection;
    }



}
