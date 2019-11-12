package model;

import model.exceptions.InvalidContentTypeException;
import ui.ListOfContentScannerInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
            listOfContent.add(ContentFactory.getContent(type, s));
        }

    }

    @Override
    //REQUIRES: save file and given type must be "Movie" or "TVShow"
    //EFFECTS: saves TVShows/movies to save file from List
    public void save(String type) throws IOException, InvalidContentTypeException {
        Path path = getPath(type);
        StringBuilder toWrite = new StringBuilder();
        for (Content content: listOfContent) {
            toWrite.append(content.getTitle());
            toWrite.append(System.lineSeparator());
        }
        Files.write(path, toWrite.toString().getBytes());
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


}
