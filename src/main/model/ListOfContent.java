package model;

import model.exceptions.InvalidContentTypeException;
import ui.ListOfContentScannerInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

//Represents a list of TV Shows
public class ListOfContent extends ListOfContentScannerInput implements Loadable, Saveable {

    public static final String TV_SHOW = "TVShow";
    public static final String MOVIE = "Movie";

    //EFFECTS: Creates empty TV show list
    public ListOfContent() {
        listOfContent = new ArrayList<>();
    }

    //EFFECTS: Returns number of items in TV show list
    public int size() {
        return listOfContent.size();
    }


    public Content getContent(int index) {
        return listOfContent.get(index);
    }


    @Override
    //REQUIRES: TVShow save file and given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: loads TVShows/movies in save file to List
    public void load(String type) throws IOException, InvalidContentTypeException {
        Path path = getPath(type);
        List<String> loadedList = Files.readAllLines(path);
        for (String s : loadedList) {
            String[] params = s.split("_");
            String title = params[0];
            String release = params[1];
            int rating = Integer.parseInt(params[2]);
            long tvdbID = Long.parseLong(params[3]);
            int numEpisodes = Integer.parseInt(params[4]);
            String posterUrl = params[5];
            listOfContent.add(new TVShow(title, release, rating, tvdbID, numEpisodes, posterUrl));
        }

    }

    @Override
    //REQUIRES: save file and given type must be "Movie" or "TVShow"
    //EFFECTS: saves TVShows/movies to save file from List
    public void save(String type) throws IOException, InvalidContentTypeException {
        Path path = getPath(type);
        StringBuilder toSave = new StringBuilder();
        for (Content content: listOfContent) {
            StringJoiner toWrite = new StringJoiner("_");
            TVShow t = (TVShow)content;
            toWrite.add(t.getTitle());
            toWrite.add(t.getReleaseDate());
            toWrite.add(Integer.toString(t.getRating()));
            toWrite.add(Long.toString(t.getTvdbID()));
            toWrite.add(Integer.toString(t.getNumEpisodes()));
            toWrite.add(t.getPosterURL());
            toSave.append(toWrite.toString());
            toSave.append(System.lineSeparator());
        }
        Files.write(path, toSave.toString().getBytes());
    }

    private Path getPath(String type) throws InvalidContentTypeException {
        Path path;
        if (type.equals(TV_SHOW)) {
            path = Paths.get("data/TVShowsSave");
        } else if (type.equals(MOVIE)) {
            path = Paths.get("data/MoviesSave");
        } else {
            throw new InvalidContentTypeException();
        }
        return path;
    }

    public void removeContent(Content c) {
        listOfContent.remove(c);
    }



    @Override
    public Iterator<Content> iterator() {
        return listOfContent.iterator();
    }
}
