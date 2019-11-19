package network;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.exceptions.ShowNotFoundException;

import java.io.IOException;

public class TVDbAuthorization {

    public String getAuthToken() {
        return authToken;
    }

    private String authToken;

    public TVDbAuthorization() throws IOException, ShowNotFoundException {
        authToken = parseForAuthToken();
    }

    private String parseForAuthToken() throws IOException, ShowNotFoundException {
        HttpRequester httpRequester = new HttpRequester();
        String authJson = httpRequester.makeHttpRequest("https://api.thetvdb.com/login");
        JsonObject o = new Gson().fromJson(authJson, JsonObject.class);
        return o.get("token").getAsString();
    }





}
