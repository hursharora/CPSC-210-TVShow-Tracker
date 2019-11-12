package network;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonObject;
import model.exceptions.ShowNotFoundException;


public class HttpRequester {

    public String makeHttpRequest(String url) throws IOException, ShowNotFoundException {
        return makeHttpRequest(new URL(url));
    }


    protected String makeHttpRequest(URL url) throws IOException, ShowNotFoundException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = getURlConnection(url);
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder out = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                out.append(line);
                line = reader.readLine();
            }
        }
        return out.toString();

    }

    protected HttpURLConnection getURlConnection(URL url) throws IOException, ShowNotFoundException {
        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(createAuthJson());
        wr.flush();
        wr.close();
        return urlConnection;

    }

    private String createAuthJson() {
        JsonObject authJson = new JsonObject();
        authJson.addProperty("apikey", "HDQUM3I93ZPRH9M3");
        return authJson.toString();
    }


}
